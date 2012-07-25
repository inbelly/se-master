package com.megalogika.sv.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.megalogika.sv.model.E;
import com.megalogika.sv.model.Product;
import com.megalogika.sv.util.TextSearchStringSanitizer;

@Service("eService")
@Repository
public class EService {

	@PersistenceContext
	private EntityManager em;

	/**
	 * žodžiai, į kuriuos nekreipiame dėmesio ieškodami konservantų pagal
	 * produkto sudėtį
	 */
	@Resource(name = "ignoreWords")
	private List<String> ignoreWords;

	@Resource(name = "eDictionaryName")
	private
	String eDictionaryName = "english_e";

	@Autowired
	MessageSource messages;

	public EService() {
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public E createE() {
		return new E();
	}

	@Transactional
	public E save(E e) {
		return em.merge(e);
	}

	public E load(long id) {
		return em.find(E.class, id);
	}
	
	public E load(String id) {
		return load(Long.parseLong(id));
	}

	@Transactional
	public void delete(E e) {
		for (Product p : e.getProducts()) {
			p.getConservants().remove(e);
		}
		em.remove(e);
	}

	@Transactional
	public E approve(E e) {
		e.setApproved(true);
		
		return e;
	}

	@SuppressWarnings("unchecked")
	public List<E> getList(ESearchCriteria criteria) {
		Assert.notNull(criteria);
		
		criteria.setTotalItems(((Long) criteria.setParameters(em.createQuery("select count(*) from E E " + criteria.getWhereClause()))
				.getSingleResult()).longValue());
		return em.createNativeQuery("select * from E e " + criteria.getWhereClause() + " order by " + criteria.getOrderByClause(), E.class)
//						.setFirstResult(criteria.getPage() * criteria.getItemsPerPage())
//						.setMaxResults(criteria.getItemsPerPage())
						.getResultList();
		
	}

	public E getOneByNameOrAlias(String string) {
		if (!StringUtils.hasText(string)) {
			return null;
		}
		List<E> l = getByNameOrAlias(string);
		if (l.size() > 0) {
			return l.get(0);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<E> getByNameOrAlias(String query) {
		query = query.trim();
		query = fixESpace(query);

		List<E> l = em.createNativeQuery(
				"select *, ts_rank_cd(to_tsvector('" + geteDictionaryName()
						+ "', coalesce(name,'')||' '||coalesce(number,'')||' '||coalesce(aliases,'')), plainto_tsquery('" + geteDictionaryName()
						+ "', :qstring)) AS rank" + " FROM e" + " WHERE to_tsvector('" + geteDictionaryName()
						+ "', coalesce(number,'')||' '||coalesce(name,'')||' '||coalesce(aliases,'')) @@ plainto_tsquery('" + geteDictionaryName() + "', :qstring)",
				E.class).setParameter("qstring", new TextSearchStringSanitizer().sanitize(query)).getResultList();

		return l;
	}

	public E createNew(String token) {
		E e = new E();
		e.setName(token);
		e.setNumber(token);
		e.setApproved(false);
		return em.merge(e);
	}
	
	public E createNew(String token, String hazard) {
		E ret = createNew(token);
		ret.setCategory(hazard);
		
		return ret;
	}

	protected transient org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

	protected List<E> detectConservants(String string) {
		List<E> ret = new ArrayList<E>();
		logger.debug("ieškom konservantų tokiame stringe: " + string);
		if (!StringUtils.hasText(string)) {
			logger.debug("nėra teksto nieko ir neradom!!!");
			return ret;
		}
		StringTokenizer t = new StringTokenizer(string, ",");
		while (t.hasMoreTokens()) {
			String token = t.nextToken().trim();
			if (ignoreWords.contains(token.toLowerCase())) {
				logger.debug("tokeno '" + token + "' neieškom, nes jis ambiguous");
			} else if (!token.matches("^.*?\\w+.*?$")) {
				logger.debug("tokeno '" + token + "' neieškom, nes jis neturi raidžių sekos?!");
			} else {
				logger.debug("ieskom tokeno: " + token);
				E e = getOneByNameOrAlias(token);
				 if (null == e) {
					 logger.debug("neradom, kuriam nauja: ");
					 e = createNew(token, E.UNKNOWN_HAZARD);
					 logger.debug("sukurem: " + e);
				 }
				if (null != e && !ret.contains(e)) {
					ret.add(e);
				}
			}
		}
		logger.debug("grazinam: " + ret.toString());
		return ret;
	}

	public Map<String, String> getHazardDescriptions() {
		return createE().getHazardDescriptions();
	}

	public String getHazardDescription(String hazard) {
		return messages.getMessage(getHazardDescriptions().get(hazard), null, null);
	}

	@SuppressWarnings("unchecked")
	public Collection<E> findConservants(String q) {
		q = q.trim();

		q = fixESpace(q);

		List<E> l = em
				.createQuery(
						"select e from E e where approved IS TRUE and (lower(trim(number)) like 'e'||:q or lower(trim(number)) like :q or lower(trim(name)) like :q) order by e.number asc")
				.setParameter("q", q + "%").getResultList();

		if (l.size() == 0 && q.length() > 6) {
			l = em.createQuery("select e from E e where approved IS TRUE and (lower(aliases) like lower(:q))").setParameter("q", "%" + q + "%").getResultList();
			if (l.size() == 0) {
				l = getByNameOrAlias(q);
			}
		}

		return l;
	}

	private String fixESpace(String q) {
		if (q.startsWith("e ") || q.startsWith("e-")) {
			String t = q.substring(2);
			if (t.matches("^\\d+$")) {
				q = t;
			}
		}
		return q;
	}

	public void seteDictionaryName(String eDictionaryName) {
		this.eDictionaryName = eDictionaryName;
	}

	public String geteDictionaryName() {
		return eDictionaryName;
	}

	public String clearDuplicates(String value) {
		if (! StringUtils.hasText(value)) {
			return value;
		}
		StringTokenizer t = new StringTokenizer(value, ",");
		String ret = new String();
		while (t.hasMoreTokens()) {
			String token = t.nextToken().trim();
			logger.debug("NEXT TOKEN: " + token + " RET: " + ret);
			if (! ret.toLowerCase().contains(token.toLowerCase())) {
				ret += token + ", ";
			}
		}
		
		ret = ret.substring(0, ret.length() - 2);
		
		return ret;
	}
}

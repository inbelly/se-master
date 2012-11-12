package com.megalogika.sv.service;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.megalogika.sv.model.E;
import com.megalogika.sv.model.Product;
import com.megalogika.sv.service.filter.RankingFilterException;

@Service("searchService")
public class SearchService {
	@Autowired
	ProductService productService;
	@Autowired
	EService eService;
	static final transient Logger logger = Logger
			.getLogger(SearchService.class);

	public SearchResults search(ProductSearchCriteria criteria) {
		Assert.notNull(criteria);

		logger.debug("HELLLLOOOOO!!!!! " + criteria);

		SearchResults results = new SearchResults();
		results.setQuery(criteria.getQ());

		if (criteria.hasQuery()) {
			if (criteria.getQ().trim().matches("^\\d+$")) {
				logger.debug("vieni skaičiai! " + criteria.getQ());
				if (productService.isValidBarcode(criteria.getQ().trim())) {
					logger.debug("TAI JUK TEISINGAS BARKODAS!!!");
					results.setSearchedByBarcode(true);
					results.setProducts(productService.getByBarcode(Long
							.parseLong(criteria.getQ().trim())));
					results.setProduct(results.getProducts().size() > 0);
				}
			} else {
				logger.debug("ieskom konservanto");
				results.setConservants(eService.getByNameOrAlias(criteria
						.getQ()));
				if (null != results.getConservants()
						&& results.getConservants().size() > 0) {
					// jei radom tiksliai tokiu konservantu, grazinam tuos
					// konservantus
					// ir produktus, juos turincius
					logger.debug("radom konservanta ");
					results.setConservant(true);

					criteria.getFilters().clear();

					if (!criteria.hasEFilter()) {
						for (E e : results.getConservants()) {
							criteria.addEFilter(e.getId(), e.getName());
						}
					}
					results.setProducts(productService.getList(criteria, false));
					results.setProduct(results.getProducts().size() > 0);
				} else {
					logger.debug("neradom konservanto, ieskom produktu...");
					try {
						criteria.addTextKeywordFilter(criteria.getQ());
					} catch (RankingFilterException e) {
						// nothing
					}
					results.setProducts(productService.getProductList(criteria));

					logger.debug("RADOM VA KIEK: "
							+ results.getProducts().size());

					results.setProduct(results.getProducts().size() > 0);

					// if (criteria.getQ().contains(" ")) {
					// StringTokenizer t = new StringTokenizer(criteria.getQ(),
					// " \t\n\r\f,.-:;");
					// // TODO pabaigti skaidant i gabalus
					// }
				}
			}
		}

		return results;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public EService getEService() {
		return eService;
	}

	public void setEService(EService service) {
		eService = service;
	}

	public List<Product> getList(ProductSearchCriteria criteria, boolean addRandomUnapprovedProduct) {
		return productService.getList(criteria, addRandomUnapprovedProduct);
	}

	public List<Product> getProductList(ProductSearchCriteria criteria) {
		return productService.getProductList(criteria);
	}

	public List<Product> getSuggestList(ProductSearchCriteria criteria) {
		return productService.getList(criteria, false);
	}
	
	public Product getRandomUnapprovedProduct() {
		return productService.getRandomUnapprovedProduct();
	}

}

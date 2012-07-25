package com.megalogika.sv.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.megalogika.sv.model.Comment;
import com.megalogika.sv.model.Product;
import com.megalogika.sv.model.User;

@Service("commentService")
@Repository
public class CommentService {
	
	protected transient Logger logger = Logger.getLogger(CommentService.class);
	
	@PersistenceContext
	private EntityManager em;
	
	public Comment createComment(User u, Product p) {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		if (null != a && null != a.getPrincipal() && a.getPrincipal() instanceof User && a.isAuthenticated()) {
			Comment ret = new Comment();
			ret.setName(((User) a.getPrincipal()).getNick());
			ret.setEmail(((User) a.getPrincipal()).getEmail());
			
			return ret;
		}
		return null;
	}
	
	public Comment load(String id) {
		return em.find(Comment.class, Long.parseLong(id));
	}

	@Transactional
	public void addCommentToProduct(Product p, Comment comment) {
		comment.setProduct(p);
		p.addComment(comment);
	}

	@Transactional
	public void deleteComment(Product p, Comment comment) {
		p.removeComment(comment);
		em.remove(comment);
	}
	
	public void setEm(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEm() {
		return em;
	}
	
}

package com.megalogika.sv.service;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.megalogika.sv.model.User;

public class ESearchCriteria extends SearchCriteria implements Serializable {
	private static final long serialVersionUID = 1254804227109019779L;
	
	public final static String ORDER_BY_CATEGORY = "category";
	public final static String ORDER_BY_PRODUCT_COUNT = "productCount";
	public final static String ORDER_BY_NUMBER = "number";
	
	public ESearchCriteria() {
		super();
		setOrderBy(ORDER_BY_CATEGORY);
		logger.debug("CREATING E SEARCH CRITERIA, ORDERED BY " + getOrderBy() );
	}
	
	@Override
	public String getOrderByClause() {
	    List<String> orderClauses = new LinkedList<String>();
	    if (hasAuthority(User.ROLE_ADMIN)) {
           orderClauses.add("e.approved asc");
        }
        if (ORDER_BY_NUMBER.equals(orderBy)) {
            orderClauses.add(getOrderByNumber());
        } else if (ORDER_BY_CATEGORY.equals(orderBy)) {
            orderClauses.add("category desc");
            orderClauses.add(getOrderByNumber());
        } else if (ORDER_BY_PRODUCT_COUNT.equals(orderBy)) {
            orderClauses.add(getOrderByNumber());
            orderClauses.add(getOrderByProductCount());
        }
	    
        String ret = StringUtils.join(orderClauses, ", ");
        logger.debug("E!!! THE CLAUSE IS: " + ret);
		return ret;
	}
	
	private String getOrderByNumber(){
//		return "to_number(btrim(lower(number), '():;- abcdefghijklmnopqrstwvuxyząčęėįšųūž') || '0', '99999') asc, lower(number) asc";
		return "number asc";
	}
	
	private String getOrderByProductCount(){
		return "(select count( distinct products_id) from product_e where conservants_id = e.id ) desc";
	}

	@Override
	public String getWhereClause() {
		StringBuffer ret = new StringBuffer();

		if (hasAuthority(User.ROLE_ADMIN)) {
			// kai useris prisiregistraves, rodom jam viska
		} else { // kai ne, rodom jam tik approvintus
			ret.append(" where e.approved = true ");
		}
		return ret.toString();
	}

}

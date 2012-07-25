package com.megalogika.sv.service.filter;

import org.springframework.util.StringUtils;


public class TextNameKeywordFilter extends TextKeywordFilter implements Filter, RankingFilter {

	private static final long serialVersionUID = -7579583635389052735L;
	
	public TextNameKeywordFilter() {
		super();
	}
	
	public TextNameKeywordFilter(String query, boolean firstFilter) {
		super(query, firstFilter);
	}

	@Override
	public String getDescription() {
		return "TextNameKeywordFilter.desc";
	}
	
	@Override
	public String getFilterClause() {
		if (StringUtils.hasText(getKeyword())) {
			return 
			(isFirstFilter() ? "" : " and") +  
			"(" +
				"coalesce(p.name,'') @@ plainto_tsquery('" + getDictionaryName() + "', :keyword)" +
			")";
		} else {
			return "(:keyword is null)";
		}
	}
	
	@Override
	public String getRankClause() {
		if (StringUtils.hasText(getKeyword())) {
			return ", ts_rank_cd(to_tsvector(coalesce(p.name,'')), plainto_tsquery('" + getDictionaryName() + "', :keyword)) AS rank";
		} else {
			return "";
		}
	}
}

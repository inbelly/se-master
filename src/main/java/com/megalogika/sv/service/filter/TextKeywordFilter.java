package com.megalogika.sv.service.filter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import com.megalogika.sv.util.TextSearchStringSanitizer;

public class TextKeywordFilter extends KeywordFilter implements Filter, RankingFilter {
	private static final long serialVersionUID = 26512847832677424L;
	
	@Resource(name = "dictionaryName")
	private String dictionaryName = "english";
	
	public TextKeywordFilter() {
		super();
	}
	
	@Override
	public String getDescription() {
		return "TextKeywordFilter.desc";
	}
	
	public TextKeywordFilter(String query, boolean firstFilter) {
		super(query, firstFilter);
		setKeyword(new TextSearchStringSanitizer().sanitize(query));
	}

	@Override
	public Map<String, Object> getParameterMap() {
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("keyword", getKeyword());
		return ret;
	}
	
	@Override
	public String getFilterClause() {
		if (StringUtils.hasText(getKeyword())) {
			return 
			(isFirstFilter() ? "" : " and") +  
			"(" +
				"to_tsvector('" + getDictionaryName() + "', coalesce(p.name,'')||' '||coalesce(p.conservantstext,'')||' '||coalesce(p.tags,'')||' '||coalesce(pc.name,'')||' '||coalesce(p.company,'')) @@ plainto_tsquery('" + getDictionaryName() + "', :keyword)" +
			")";
		} else {
			return "(:keyword is null)";
		}
	}
	
	@Override
	public String getRankClause() {
		if (StringUtils.hasText(getKeyword())) {
			return ", ts_rank_cd(to_tsvector('" + getDictionaryName() + "', coalesce(p.name,'')||' '||coalesce(p.conservantstext,'')||' '||coalesce(p.tags,'')||' '||coalesce(pc.name,'')||' '||coalesce(p.company,'')), plainto_tsquery('" + getDictionaryName() + "', :keyword)) AS rank";
		} else {
			return "";
		}
	}

	@Override
	public String getRankColumn() {
		if (StringUtils.hasText(getKeyword())) {
			return " rank desc, ";
		} else {
			return "";
		}
	}

	public void setDictionaryName(String dictionaryName) {
		this.dictionaryName = dictionaryName;
	}

	public String getDictionaryName() {
		return dictionaryName;
	}
}

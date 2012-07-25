package com.megalogika.sv.util;

import java.util.StringTokenizer;

import org.springframework.util.StringUtils;

public class TextSearchKeywordTokenizer {
	
	TextSearchStringSanitizer sanitizer;
	
	public TextSearchKeywordTokenizer() {
		sanitizer = new TextSearchStringSanitizer();
	}
	
	public String tokenizeTsKeyword(String query) {
		if (StringUtils.hasText(query)) {
			StringTokenizer tokenizer = new StringTokenizer(query, " ");
			StringBuffer tsKeyword = new StringBuffer();
			
			tsKeyword.append(getNextToken(tokenizer));
			
			while (tokenizer.hasMoreTokens()) {
				tsKeyword.append(" | ").append(getNextToken(tokenizer));
			}
			
			return tsKeyword.toString();
		} else {
			return null;
		}
	}
	
	private String getNextToken(StringTokenizer tokenizer) {
		String token = tokenizer.nextToken();
		return sanitizer.sanitize(token);
	}
	
}

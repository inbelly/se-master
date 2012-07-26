package com.megalogika.sv.util;

public class TextSearchStringSanitizer {

	public String sanitize(String token) {
		if (null == token) {
			return null;
		}
		return token.replace("'", "\\'").replace("&", "\\&").replace("|", "\\|").replace("!", "\\!");
	}

}

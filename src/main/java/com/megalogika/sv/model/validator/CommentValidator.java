package com.megalogika.sv.model.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import com.megalogika.sv.model.Comment;

@Component
public class CommentValidator {
	public void validateComment(Comment c, Errors e) {
		if (!StringUtils.hasText(c.getBody())) {
			e.rejectValue("body", "comment.error.notext", "Užpildykite komentaro tekstą");
		}
		if (!StringUtils.hasText(c.getName())) {
			e.rejectValue("body", "comment.error.noname", "Nurodykite savo vardą");
		}
	}

}

package com.megalogika.sv.model.validator;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.megalogika.sv.model.E;

@Component("eValidator")
public class EValidator {
	public void validateEform(E e, MessageContext context) {
		if (!StringUtils.hasText(e.getNumber())) {
			context.addMessage(new MessageBuilder().error().defaultText("Turi buti nurodyts E numeris!").code("e.error.nonumber").build());
		}
	}
}

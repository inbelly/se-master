package com.megalogika.sv.service;

import java.util.StringTokenizer;

import ml.walrus.service.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.megalogika.sv.model.Product;
import com.megalogika.sv.model.User;

@Service("emailActions")
public class EmailActions {
	@Autowired(required = true)
	MailService mailService;

	@Autowired
	MessageSource messages;

	@Autowired
	EmailConfig emailConfig;

	String productProblemEmail = "mail.body.productProblemEmail";
	String productProblemSubject = "mail.subject.productProblemsubject";

	String sendProductEmail = "mail.body.sendProductEmail";
	String sendProductSubject = "mail.subject.sendProductSubject";

	String sendProductApproveEmail = "mail.body.sendProductApprove";
	String sendProductApproveSubject = "mail.subject.sendProductApproveSubject";

	String sendNewPasswordEmail = "mail.body.sendNewPasswordEmail";
	String sendNewPasswordSubject = "mail.subject.sendNewPasswordSubject";

	public void sendProductProblemEmail(Product product, String text) {
		text += messages.getMessage(productProblemEmail,
				new Object[] { emailConfig.getProductLink(this, product) },
				null);
		try {
			mailService
					.sendEmail("susikaupk@gmail.com", messages
							.getMessage(productProblemSubject, null, null),
							text, emailConfig.getFromEmail(), emailConfig
									.getFromName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendProductLink(Product product, String to, String text) {
		text += messages.getMessage(sendProductEmail,
				new Object[] { messages.getMessage("site.name", null, null),
						emailConfig.getProductLink(this, product) }, null);

		StringTokenizer t = new StringTokenizer(to, " ,;");
		while (t.hasMoreTokens()) {
			try {
				mailService.sendEmail(
						t.nextToken(),
						messages.getMessage(sendProductSubject, new Object[] {
								messages.getMessage("site.domain", null, null),
								product.getName() }, null), text,
						emailConfig.getFromEmail(), emailConfig.getFromName());
			} catch (Exception e) {
				// NOTHING
			}
		}

	}

	public void sendProductApproveEmail(Product product) {
		if (null == product.getUser() || null == product.getUser().getEmail()
				|| !StringUtils.hasText(product.getUser().getEmail())) {
			return;
		}
		String text = messages.getMessage(
				sendProductApproveEmail,
				new Object[] { messages.getMessage("site.name", null, null),
						messages.getMessage("site.domain", null, null),
						product.getName().trim(),
						emailConfig.getProductUrl(product) }, null);
		try {
			mailService
					.sendEmail(product.getUser().getEmail(), messages
							.getMessage(sendProductApproveSubject, null, null),
							text, emailConfig.getFromEmail(), emailConfig
									.getFromName());
		} catch (Exception e) {
			// NOTHING
		}
	}

	public void sendNewPassword(User u, String newPassword) {
		Assert.notNull(u, "user should not be null to sendNewPassword");
		Assert.hasText(newPassword,
				"new password should not be empty string to sendNewPassword");

		String text = messages.getMessage(sendNewPasswordEmail,
				new Object[] { messages.getMessage("site.name", null, null),
						messages.getMessage("site.domain", null, null),
						newPassword, emailConfig.getLoginUrl() }, null);

		try {
			mailService.sendEmail(u.getEmail(), messages.getMessage(
					sendNewPasswordSubject, new Object[] { messages.getMessage(
							"site.domain", null, null) }, null), text,
					emailConfig.getFromEmail(), emailConfig.getFromName());
		} catch (Exception e) {
			// NOTHING
		}
	}
}

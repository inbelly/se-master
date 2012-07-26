package com.megalogika.sv.model.validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import com.megalogika.sv.model.User;
import com.megalogika.sv.service.UserService;

@Component
public class UserValidator {
	@Autowired
	UserService userService;

	public void validateRegisterForm(User u, Errors errors) {
		Assert.notNull(u, "Vartotojas negali būti NULL!!!");
		if (!StringUtils.hasText(u.getNick())) {
			errors.rejectValue("nick", "user.error.nonick", "Nenurodytas vardas.");
		}
		if (!StringUtils.hasText(u.getEmail())) {
			errors.rejectValue("email", "user.error.noemail", "Nenurodytas el. pašto adresas.");
		} else {
			try {
				InternetAddress a = new InternetAddress(u.getEmail());
				a.validate();
			} catch (AddressException e) {
				errors.rejectValue("email", "user.error.bademail", "Nekorektiškas el. pašto adresas.");
			}
			if (userService.emailExists(u.getEmail())) {
				errors.rejectValue("email", "user.error.emailexists", "Vartotojas su tokiu el. pašto adresu jau yra prisiregistravęs sistemoje.");
			}
		}
		if (!StringUtils.hasText(u.getPassword())) {
			errors.rejectValue("password", "user.error.nopassword", "Nenurodytas slaptažodis.");
		} else {
			if (!(StringUtils.hasText(u.getPassword()) && StringUtils.hasText(u.getPasswordVerify()) && u.getPassword().equals(u.getPasswordVerify()))) {
				errors.rejectValue("password", "user.error.passwordsdontmatch", "Nesutampa slaptažodžiai.");
			}
		}
		if (StringUtils.hasText(u.getNick()) && userService.nickExists(u.getNick())) {
			errors.rejectValue("nick", "user.error.nickexists", "Vartotojas su tokiu slapyvardžiu jau yra prisiregistravęs sistemoje.");
		}
		if (! u.isAggree()) {
			errors.rejectValue("aggree", "user.error.doesNotAggree", "You have to aggree to the terms and conditions to register");
		}
	}
}

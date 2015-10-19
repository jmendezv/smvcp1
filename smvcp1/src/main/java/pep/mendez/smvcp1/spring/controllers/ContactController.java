package pep.mendez.smvcp1.spring.controllers;

import java.lang.reflect.InvocationTargetException;

import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pep.mendez.smvcp1.spring.formbeans.ContactBean;
import pep.mendez.smvcp1.spring.model.entities.Contact;
import pep.mendez.smvcp1.spring.model.service.ContactService;
import pep.mendez.smvcp1.utils.Utility;
import pep.mendez.smvcp1.utils.UtilityConstants;

/**
 * @author pep
 *
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = { "classpath:application.properties" }) })
public class ContactController {

	private static final Logger logger = LoggerFactory
			.getLogger(RegisterController.class);

	@Autowired
	Environment env;

	@Autowired
	MessageSource messageSource;

	@Autowired(required = true)
	private ContactService contactService;

	@Autowired(required = true)
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	JavaMailSender mailSender;

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String registerPage(ContactBean contactBean) {
		// contactBean is added to de model automatically
		return "contact";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String registerForm(
			@Valid @ModelAttribute("contactBean") ContactBean contactBean,
			BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action) throws IllegalAccessException, InvocationTargetException {

		logger.debug(contactBean.toString());

		if (action.equals("cancel")) {
			return "login";
		}

		if (bindingResult.hasErrors()) {
			return "contact";
		}

		String userName = contactBean.getUserName();

		Contact contact = contactService.findByuserName(userName);
		
		if(contact == null) {
			
			contact = new Contact();
			
			BeanUtils.copyProperties(contact, contactBean);
			
			contactService.save(contact);
			
//			String md5Hex = DigestUtils.md5DigestAsHex((userName + Utility.SALT)
//					.getBytes());
//
//			StringBuilder body = new StringBuilder(
//					UtilityConstants.VALIDATE_MESSAGE_1);
//			body.append(userName).append(";d=").append(md5Hex)
//					.append(UtilityConstants.VALIDATE_MESSAGE_2);
//
//			String from = env.getProperty("mailserver.replyTo");
//
//			Utility.sendEmail(mailSender, from, userName, "Registro",
//					body.toString());

		}
		
		
		return "login";
	}

}

package pep.mendez.smvcp1.spring.controllers;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pep.mendez.smvcp1.spring.formbeans.ProfileBean;
import pep.mendez.smvcp1.spring.model.entities.Profile;
import pep.mendez.smvcp1.spring.model.entities.User;
import pep.mendez.smvcp1.spring.model.service.UserService;

/*
 * We can create our custom validator implementations by two ways:
 * 
 * First we can create an annotation that conforms to the JSR-303 specs and implements its Validator class
 * 
 * Second approach is to implement the org.springframework.validation.Validator interface and set it as a validator in the Controller class using @InitBinder
 * 
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = { "classpath:application.properties" }) })
public class ProfileController {

	private static final Logger logger = LoggerFactory
			.getLogger(RegisterController.class);

	@Autowired
	Environment env;

	@Autowired
	MessageSource messageSource;

	@Autowired(required = true)
	private UserService userService;

	@Autowired(required = true)
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	JavaMailSender mailSender;

//	@Autowired
//	@Qualifier("profileValidator")
//	private Validator validator;

	/*
	 * Annotation that identifies methods which initialize the WebDataBinder
	 * which will be used for populating command and form object arguments of
	 * annotated handler methods.
	 */
	// @InitBinder
	// private void initBinder(WebDataBinder binder) {
	// binder.setValidator(validator);
	// }

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String registerPage(Principal principal, ModelMap model) {
		
		User user = userService.findByUserName(principal.getName());
		Profile profile = user.getProfile();
		ProfileBean profileBean = new ProfileBean();
		if (profile != null) {
			profileBean.setCity(profile.getCity());
			profileBean.setName(profile.getName());
			profileBean.setPhone(profile.getPhone());
			profileBean.setProfession(profile.getProfession());
		}
		model.addAttribute("profileBean", profileBean);
		return "profile";
	}

	/**
	 * Form processing
	 * 
	 * @param profile
	 *            Profile to save
	 * @param id
	 *            Id from the user to associate this profile with
	 * @return ModelAndView with the next page
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profileForm(
			@Valid @ModelAttribute("profileBean") ProfileBean profileBean,
			BindingResult bindingResult,
			// @ModelAttribute("employee") @Validated ProfileBean profileBean,
			// BindingResult bindingResult,
			HttpServletRequest request, Principal principal,
			@RequestParam(value = "action", required = true) String action,
			ModelMap model) {

		User user = userService.findByUserName(principal.getName());
		// home need this attribute
		model.addAttribute("user", user);

		if (action.equals("cancel")) {
			return "home";
		}

		if (bindingResult.hasErrors()) {
			return "redirect:profile";
		}

		String ip = request.getRemoteAddr();

		// guardar profile
		Profile profile = user.getProfile();
		if (profile == null) {
			profile = new Profile();
			user.setProfile(profile);
			profile.setUser(user);
		}
		profile.setName(profileBean.getName());
		profile.setCity(profileBean.getCity());
		profile.setPhone(profileBean.getPhone());
		profile.setIp(ip);
		profile.setDate(new Date());
		profile.setProfession(profileBean.getProfession());
		// save will create or update accordinly
		userService.save(user);

		return "home";

	}

}

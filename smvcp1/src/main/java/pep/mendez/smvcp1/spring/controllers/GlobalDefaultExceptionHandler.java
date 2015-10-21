package pep.mendez.smvcp1.spring.controllers;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import pep.mendez.smvcp1.utils.UtilityConstants;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	public static final String DEFAULT_ERROR_VIEW = "exception";

	private static final Logger logger = LoggerFactory
			.getLogger(UtilityConstants.PACKAGE);

	/*
	 * @ExceptionHandlers don't have access to neither Exception non Model
	 * objects however, Exceptions object are available to @ControlAdvice
	 * annotated controller
	 */
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request,
			HttpServletResponse response, Principal principal,
			Exception exception) {

		logger.error(exception.getMessage());

		ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);
		mav.addObject("timestamp", new Date());
		mav.addObject("request", request);
		mav.addObject("response", response);
		mav.addObject("principal", principal);
		mav.addObject("exception", exception);
		mav.addObject("error", exception.getMessage());
		mav.addObject("url", request.getRequestURL().toString());

		return mav;
	}

}

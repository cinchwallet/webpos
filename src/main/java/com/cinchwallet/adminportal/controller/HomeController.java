package com.cinchwallet.adminportal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cinchwallet.adminportal.constant.Constants;
import com.cinchwallet.adminportal.security.User;
import com.cinchwallet.adminportal.service.POSService;
import com.cinchwallet.adminportal.util.EmailSender;
import com.cinchwallet.adminportal.util.PasswordGenerator;
import com.cinchwallet.adminportal.util.Util;

@Controller
@SessionAttributes("user")
public class HomeController {

	@Autowired
	POSService posService;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is protected page!");
		model.setViewName(Constants.PAGE_DASHBOARD);
		Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication);
		User user = (User)authentication.getPrincipal();
		model.addObject("user", user);
		return model;
	}

	
	@RequestMapping(value = "/rstpass", method = RequestMethod.GET)
	public ModelAndView resetPassword() {
		ModelAndView model = new ModelAndView();
		model.setViewName(Constants.PAGE_RESET_PASSWORD);
		return model;
	}

	@RequestMapping(value = "/rstpass", method = RequestMethod.POST)
	public ModelAndView resetPasswordConfirm(HttpServletRequest  httpRequest) {
		ModelAndView model = new ModelAndView();
		model.setViewName(Constants.PAGE_RESET_PASSWORD);
		String userName = null;
		userName = httpRequest.getParameter("username");
		System.out.println("Username *** "+userName);
		// read the record from user_login table, reset the password and send the new password in email.	
		String password = PasswordGenerator.generatePswd();
		posService.updatePassword(userName, Util.getMD5(password));
		
		String email = posService.getStoreEmail(userName);
		//send email for new password
		boolean success = EmailSender.sendEmail(userName, password, email);
		if(success){
			model.addObject("msg", "New password has been sent at "+email);
		} else {
			model.addObject("error", "Email could not be send. Try again or call support team");
		}
		return model;
	}

	
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName(Constants.PAGE_LOGIN);

		return model;

	}

	

}
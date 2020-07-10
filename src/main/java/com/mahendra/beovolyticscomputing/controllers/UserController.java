package com.mahendra.beovolyticscomputing.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mahendra.beovolyticscomputing.entities.User;
import com.mahendra.beovolyticscomputing.services.SecurityService;
import com.mahendra.beovolyticscomputing.services.UserService;
import com.mahendra.beovolyticscomputing.util.EmailUtil;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	EmailUtil emailUtil;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private SecurityService securityService;

	private static final Logger logger = Logger.getLogger(UserController.class);
	private static final String CLASS_NAME = "UserController.";

	/* Show Log In Page */
	@RequestMapping("/")
	public String showLogin(@RequestParam(required = false) String error) {
		return "login";
	}

	/* Show Sign Up Page */
	@RequestMapping("/signup")
	public String showSignUp() {
		return "sign-up";
	}

	/* Register USer */
	@PostMapping("/create-user")
	public ModelAndView addUser(@Valid @ModelAttribute("User") User user, BindingResult bindingResult) {
		String msg = "";
		ModelAndView mView = new ModelAndView();
		user.setPassword(encoder.encode(user.getPassword()));
		user.setActive(true);
		System.out.println(user.getPassword() + "Password");
		try {
			User dbuser = userService.findUserByUsername(user.getUsername());
			if (dbuser != null) {
				msg = "User exists";
				System.out.println(msg);
				mView.addObject("msg", msg);
			}
			msg = "You have successfully registered";
			mView.addObject("msg", msg);
			// Create User
			User userCreated = userService.createUser(user);

			/* Send Email To User After Successfull Registration */
			String subject = "Welcome Email";
			String body = "Welcome " + user.getUsername() + " You have successfully regestered.";
			emailUtil.sendEmail(user.getEmailId(), subject, body);
			msg = "User Saved with id " + userCreated.getId();

			/* Redirect to login page after successfull login */
			mView.setViewName("redirect:/");

		} catch (Exception e) {
			logger.error("Exception in " + CLASS_NAME + "addUser", e);
		}

		return mView;
	}

	/* ModelAtrribute for user */
	@ModelAttribute("User")
	public User getUser(HttpServletRequest request, @RequestParam Map<String, String> params) throws ParseException {

		User user = new User();
		if (MapUtils.isNotEmpty(params) && ((request.getServletPath().contains("create-user")))) {
			try {
				if (StringUtils.isNotBlank(params.get("username"))) {
					user.setUsername(params.get("username"));
				}

				if (StringUtils.isNotBlank(params.get("password"))) {
					user.setPassword(params.get("password"));
				}
				if (StringUtils.isNotBlank(params.get("email_id"))) {
					user.setEmailId(params.get("email_id"));
				}

			} catch (Exception e) {
				logger.error("Exception in " + CLASS_NAME + " getUser()", e);
			}
		}
		return user;
	}

	@GetMapping("/welcome")
	public String showWelcomePage(ModelMap model, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<String> userSession = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		try {
			if (userSession == null) {
				userSession = new ArrayList<>();
			}

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			String name = auth.getName();
			model.addAttribute("username", name);

			User user = userService.findUserByUsername(name);
			if (user != null) {
				model.addAttribute("userSession", userSession);
				model.addAttribute("user", user);
				model.addAttribute("session", session.getId());
			}
		} catch (Exception e) {
			logger.error("Exception in " + CLASS_NAME + " showWelcomePage()", e);
		}
		return "welcome";
	}

	@GetMapping("/error-page")
	public String showErrorPage(Model model) {
		return "error";
	}

	/* Logout Functionality */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/?logout";
	}

	@RequestMapping(value = "/do_login", method = RequestMethod.POST)
	public ModelAndView newUser(@RequestParam(required = true) String username,
			@RequestParam(required = true) String password, HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		@SuppressWarnings("unchecked") List<String> userSession = (List<String>)
				  request.getAttribute("MY_SESSION_MESSAGES");

				if(userSession==null)
				{
					userSession = new ArrayList<>();
					request.getSession().setAttribute("MY_SESSION_MESSAGES", userSession);
				}
				
				boolean loginResponse = securityService.login(username, password); 
				{
					if (loginResponse) {
						request.getSession().setAttribute("MY_SESSION_MESSAGES", userSession);
						mView.setViewName("redirect:/welcome");
						userSession.add(username);request.getSession().setAttribute("MY_SESSION_MESSAGES",userSession);
					} else {
						mView.setViewName("redirect:/error-page");
					}
				}
		return mView;
	}



}

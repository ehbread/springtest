package com.example.test.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.login.repository.MemberRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

	@Autowired
	MemberRepository memberRepository;

	@GetMapping("/login")
	public ModelAndView login(ModelAndView mav, HttpServletRequest req) {

		String referer = req.getHeader("Referer");
		req.getSession().setAttribute("prevPage", referer);
		mav.setViewName("login/login");
		return mav;
	}

	@GetMapping("/login/fail")
	public ModelAndView fail(ModelAndView mav, HttpServletRequest req) {

		String referer = req.getHeader("Referer");
		req.getSession().setAttribute("prevPage", referer);
		mav.setViewName("login/fail");
		return mav;
	}

	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
}
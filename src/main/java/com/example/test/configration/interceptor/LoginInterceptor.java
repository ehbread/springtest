package com.example.test.configration.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.test.login.service.LoginService;
import com.example.test.search.condition.BookSearchSortEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.samskivert.mustache.Mustache;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private LoginService loginService;

	@Override
	public void postHandle(
		HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView mav) throws Exception {
		if (mav != null) {
			mav.addObject("member", loginService.getLoginInfo());
		}

		super.postHandle(req, res, handler, mav);
	}
}
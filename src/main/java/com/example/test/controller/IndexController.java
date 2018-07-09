package com.example.test.controller;

import com.example.test.login.service.LoginService;
import com.example.test.login.entity.Member;
import com.example.test.search.condition.BookSearchSortEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private LoginService loginService;

	@GetMapping
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index/index");
		mav.addObject("searchSorts", BookSearchSortEnum.values());
		return mav;
	}
}
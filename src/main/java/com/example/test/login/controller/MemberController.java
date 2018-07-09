package com.example.test.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.login.entity.Member;
import com.example.test.login.service.MemberService;

import javax.validation.Valid;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping("")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("member/create");
		return mav;
	}

	@RequestMapping("/action")
	public Member action(@Valid Member member) {

		return memberService.createMember(member);

	}
}
package com.example.test.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.test.login.entity.Member;
import com.example.test.login.repository.MemberRepository;

@Service
public class LoginService {

	@Autowired
	MemberRepository memberRepository;

	public Member getLoginInfo() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (null != auth && !"anonymousUser".equals(auth.getName())) {
			return memberRepository.findByUserId(auth.getName()).get();
		}
		return null;
	}

}
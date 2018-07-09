package com.example.test.login.service;

import java.util.Optional;

import com.example.test.login.entity.Member;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.test.login.SecurityMember;
import com.example.test.login.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		return Optional.ofNullable(memberRepository.findByUserId(userId)).filter(m -> m != null).map(m -> new SecurityMember(m.get())).get();
	}

	@Transactional(value = "transactionManager", readOnly = false)
	public Member createMember(Member member) {

		Preconditions.checkState(!memberRepository.findByUserId(member.getUserId()).isPresent(), "Duplicated ID");

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setUserPassword(passwordEncoder.encode(member.getUserPassword()));
		return memberRepository.save(member);
	}
}
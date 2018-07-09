package com.example.test.login;

import com.example.test.login.entity.Member;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
@Setter
public class SecurityMember extends User {

	private static final String ROLE_PREFIX = "ROLE_";

	public SecurityMember(Member member) {
		super(member.getUserId(), member.getUserPassword(), defaultAuthority());
	}

	private static List<GrantedAuthority> defaultAuthority() {
		return Lists.newArrayList(new SimpleGrantedAuthority(ROLE_PREFIX + "BASIC"));
	}
}
package com.example.test.configration;

import com.example.test.login.config.CustomLoginSuccessHandler;
import com.example.test.login.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MemberService memberService;


	@Override
	public void configure(WebSecurity web) throws Exception {
		// 예를들어 이런식으로 인증할것들을 풀어주는겁니다. (주로 리소스)
		web.ignoring().antMatchers("/js/**", "/webjars/**", "/booksearch/**", "/member/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 여기에선 리소스외에 페이지의 인증/비인증/인증권한등을 설정하는게 좋은것 같습니다.
		http.authorizeRequests()
			// 어드민 권한으로만 접근할 수 있는 경로.
			.antMatchers("/").hasRole("BASIC")
			.antMatchers("/**").permitAll()
			.and().formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/")
			.failureUrl("/login/fail")
			.and()
			.logout();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new CustomLoginSuccessHandler("/");
	}
}
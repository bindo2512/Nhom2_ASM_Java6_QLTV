package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.service.userService;

@Configuration
@EnableWebSecurity
public class authConfig extends WebSecurityConfiguration{
@Autowired
BCryptPasswordEncoder be;
@Autowired
userService userService;

@Bean
public BCryptPasswordEncoder getPasswordEncoder() {
	return new BCryptPasswordEncoder();
}

protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userService);
}

protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable().cors().disable();
	http.authorizeRequests()
		.antMatchers("/home/**", "/qltv/**").permitAll()
		.antMatchers("/admin/**").hasAnyRole("ADMIN")
		.antMatchers("/user/**").hasAnyRole("USER")
		.antMatchers("/checkout").authenticated();
	
	http.exceptionHandling()
		.accessDeniedPage("/auth/access_denied");
	
	http.formLogin()
		.loginPage("/qltv/login/form")
		.loginProcessingUrl("/qltv/login")
		.defaultSuccessUrl("/qltv/login/success")
		.failureUrl("/qltv/login/error")
		.usernameParameter("username")
		.passwordParameter("password");
	http.rememberMe()
		.rememberMeParameter("remember");
	http.logout()
		.logoutUrl("/qltv/logout")
		.logoutSuccessUrl("/qltv/logout/successful");
	http.httpBasic();
}
}

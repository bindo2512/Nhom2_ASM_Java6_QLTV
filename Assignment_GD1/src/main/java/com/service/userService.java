package com.service;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import com.dao.accountDAO;
import com.entity.accounts;

@Service
public class userService implements UserDetailsService {
	@Autowired accountDAO accountDAO;
	@Autowired BCryptPasswordEncoder pe;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			accounts account = accountDAO.findById(username).get();
			String password = pe.encode(account.getPassword());
			String role = account.getIsadmin();
			return User.withUsername(username).password(pe.encode(password)).roles(role).build();			
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public void loginFormOAuth2(OAuth2AuthenticationToken oauth2) {
		String username = oauth2.getPrincipal().getAttribute("name");
		String password = Long.toHexString(System.currentTimeMillis());
		UserDetails user = User.withUsername(username).password(pe.encode(password)).roles("USER").build();
		org.springframework.security.core.Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
}

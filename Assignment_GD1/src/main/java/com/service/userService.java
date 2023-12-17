package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.dao.accountDAO;
import com.entity.accounts;
import com.entity.recordEntity.loginRecord;

@Service
public class userService implements UserDetailsService {
	@Autowired accountDAO accountDAO;
	@Autowired BCryptPasswordEncoder pe;
	@Autowired accountService service;


	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			accounts account = accountDAO.findById(username).get();
			String password = account.getPassword();
			String role = account.getIsadmin()? "ADMIN":"USER";
			service.updateLastLogin(username);
			if (account.getIsactive() == true) {
				new loginRecord(account.getAccountdetail().getEmail(), account.getUsername(), account.getPassword(), account.getAccountdetail().getFullname());
				return User.withUsername(username).password(account.getPassword()).roles(role).build();	
			} else {
				return null;
			}		
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

package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
			String password = account.getPassword();
			String role = account.getIsadmin();
			return User.withUsername(username).password(pe.encode(password)).roles(role).build();			
		} catch (Exception e) {
			return null;
		}
	}
}

package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.accountDAO;
import com.entity.Account;
import com.service.CookieService;
import com.service.ParamService;
import com.service.SessionService;

@Controller
public class loginController {

	@Autowired
	accountDAO dao;
	
	@Autowired
	ParamService param;
	
	@Autowired
	CookieService cookie;
	
	@Autowired
	SessionService session;
	
	@RequestMapping("/qltv/login")
	public String loginCtrl() {
		return "forgot_and_login/login";
	}
	@RequestMapping("/qltv/register")
	public String showRegisterPage() {
		return "forgot_and_login/register";
	}
	@RequestMapping("/qltv/forgot")
	public String showForgotPWPage() {
		return "forgot_and_login/forgot";
	}
	@PostMapping("/qltv/login")
	public String loginFunc() {
		String username = param.getString("username", "");
		String password = param.getString("password", "");
		Boolean rm = param.getBoolean("remember", false);
		try {
			Account user = dao.findById(username).get();
			if (user.getPassword().equals(password)) {
				if (user.getActive() == true) {
					session.set("user", user);
					if (rm == true ) {
						cookie.add("username", username, 10);
						cookie.add("password", password, 10);
					} else {
						cookie.remove("username");
						cookie.remove("password");
					}
					return "redirect:/qltv/home";
				} else {
					
				}
			} else {
				
			}
			return "/qltv/login";
		} catch (Exception e) {
			System.out.println(e);
			return "/qltv/login";
		}
	}
}

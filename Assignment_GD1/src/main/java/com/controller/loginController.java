package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.accountDAO;
import com.entity.Account;


@Controller
public class loginController {

	@RequestMapping("/qltv/login/form")
	public String loginCtrl() {
		return "forgot_and_login/login";
	}
}

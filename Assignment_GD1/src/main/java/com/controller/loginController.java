package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping("/qltv/login/success")
	public String loginCtrlSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công");
		return "forgot_and_login/login";
	}
	
	@RequestMapping("/qltv/login/error") 
	public String loginCtrlError(Model model) {
		model.addAttribute("message", "Đăng nhập thất bại");
		return "forgot_and_login/login";
	}

}

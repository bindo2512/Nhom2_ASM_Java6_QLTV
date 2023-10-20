package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.accountDAO;
import com.entity.accounts;


@Controller
public class loginController {

	@RequestMapping("/qltv/login/form")
	public String loginCtrl() {
		return "forgot_and_login/login";
	}
	
	@RequestMapping("/qltv/login/success")
	public String loginCtrlSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công");
		return "redirect:/qltv/products";
	}
	
	@RequestMapping("/qltv/login/error") 
	public String loginCtrlError(Model model) {
		model.addAttribute("message", "Đăng nhập thất bại");
		return "forgot_and_login/login";
	}

	@RequestMapping("/qltv/login/access_denied")
	public String accessDeniedCtrl(Model model) {
		model.addAttribute("message", "Truy cập bị từ chối");
		return "forgot_and_login/login";
	}

	@RequestMapping("/qltv/logout/successful")
	public String logoutSucess(Model model) {
		model.addAttribute("message", "Đăng xuất thành công");
		return "forgot_and_login/login";
	}


}

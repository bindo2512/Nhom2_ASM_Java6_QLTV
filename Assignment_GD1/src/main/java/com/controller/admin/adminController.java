package com.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class adminController {
	@RequestMapping("dashboard")
	public String index() {
		return "admin/dashboard";
	}
	
}

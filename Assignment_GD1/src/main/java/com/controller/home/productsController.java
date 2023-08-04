package com.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qltv/")
public class productsController {
	@RequestMapping("products")
	public String productsController() {
		return "home/main";
	}
}

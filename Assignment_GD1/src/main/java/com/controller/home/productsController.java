package com.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qltv/")
public class productsController {
	@Autowired
	
	
	@RequestMapping("products")
	public String productsController() {
		List
		return "home/main";
	}
}

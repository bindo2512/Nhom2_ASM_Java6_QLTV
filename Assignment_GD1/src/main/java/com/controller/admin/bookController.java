package com.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class bookController {
	@RequestMapping("/admin/books")
	public String bookCtrl() {
		return "admin/book/books";
	}
}

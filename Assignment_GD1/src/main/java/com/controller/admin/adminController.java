package com.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Book;
import com.service.bookService;

@Controller
@RequestMapping("admin")
public class adminController {
	@Autowired
	bookService service;

	@RequestMapping("dashboard")
	public String index(Model model) {
		List<Book> list = service.findAll();
		model.addAttribute("items", list);
		return "admin/dashboard";
	}

	@RequestMapping("rental")
	public String rentalCtrl() {
		return "admin/rental/retail";
	}
}

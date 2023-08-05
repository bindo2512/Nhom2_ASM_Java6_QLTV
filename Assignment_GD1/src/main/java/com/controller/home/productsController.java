package com.controller.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.bookDAO;
import com.entity.Book;
import com.service.bookService;

@Controller
public class productsController {
	@Autowired
	bookService service;
	
	@RequestMapping("/qltv/products")
	public String productsController(Model model) {
		List<Book> list = service.findAll();
		model.addAttribute("items", list);
		return "cart/main";
	}

	@RequestMapping("/qltv/products/detail/{bookid}")
	public String productDetailController(Model model, @PathVariable("bookid") Integer id) {
		Book item = service.findById(id);
		model.addAttribute("item", item);
		return "cart/detail";
	}
}

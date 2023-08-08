package com.controller.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.Book;
import com.service.bookService;
import com.service.rentalService;

@Controller
public class productsController {
	
	@Autowired
	bookService service;
	
	@Autowired
	rentalService rentalService;

	@RequestMapping("/qltv/products")
	public String productsCtrl(Model model) {
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

	@RequestMapping("/user/cart")
	public String cartCtrl() {
		return "cart/cart";
	}

	@RequestMapping("/user/rental")
	public String rentalCtrl(@PathVariable("retailid") Integer id, Model model) {
		model.addAttribute("rental", rentalService.findById(id));
		return "cart/checkout";
	}

	@RequestMapping("/user/rental/detail/{retailid}")
	public String rentalDetailCtrl(@PathVariable("retailid") Integer id, Model model) {
		model.addAttribute("rental", rentalService.findById(id));
		return "cart/checkout";
	}
}

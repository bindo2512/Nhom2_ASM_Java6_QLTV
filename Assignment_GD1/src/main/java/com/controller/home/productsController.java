package com.controller.home;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.categoriesDAO;
import com.dao.publishersDAO;
import com.entity.books;
import com.entity.filterCriteria;
import com.service.bookService;
import com.service.rentalService;

@Controller
public class productsController {
	
	@Autowired
	bookService service;
	
	@Autowired
	rentalService rentalService;

	@Autowired
	categoriesDAO cDAO;

	@Autowired
	com.dao.authorsDAO authorsDAO;

	@Autowired
	publishersDAO pDao;

	static int page_size = 9;
	static int page_count = 0;

	@RequestMapping("/qltv/products")
	public String productsCtrl(Model model) {
		Page<books> list = service.findBookByCriteria(null, null, null, null, page_count, page_size);
		model.addAttribute("criteria", new filterCriteria());
		model.addAttribute("items", list);
		model.addAttribute("categories", cDAO.findAll());
		model.addAttribute("authors", authorsDAO.findAll());
		model.addAttribute("publishers", pDao.findAll());
		return "cart/main";
	}

	@RequestMapping("/qltv/products/detail/{bookid}")
	public String productDetailController(Model model, @PathVariable("bookid") Integer id) {
		books item = service.findById(id);
		model.addAttribute("item", item);
		return "cart/detail";
	}


	@RequestMapping("/qltv/products/pdf/{bookid}")
	public String pdfController(Model model, @PathVariable("bookid") Integer id) {
		books item = service.findById(id);
		model.addAttribute("item", item);
		return "cart/pdf";
	}

	@RequestMapping("/user/cart")
	public String cartCtrl(Model model) {
		model.addAttribute("criteria", new filterCriteria());
		return "cart/cart";
	}

	@RequestMapping("/user/rental")
	public String rentalCtrl(@PathVariable("retailid") Integer id, Model model) {
		model.addAttribute("rental", rentalService.findById(id));
		return "cart/checkout";
	}

	@RequestMapping("/user/rental/detail/{retailid}")
	public String rentalDetailCtrl(@PathVariable("retailid") Integer id, Model model) {
		model.addAttribute("rental", rentalService.findAllRetailById(id));
		model.addAttribute("rdetail", rentalService.findById(id));
		return "cart/checkoutdetail";
	}

	@RequestMapping("/user/rental/history")
	public String rentalHistoryCtrl() {
		return "rentalList/rental";
	}

	@RequestMapping("/qltv/products/search")
	public String searchCtrl(Model model, @ModelAttribute("criteria") filterCriteria criter, @RequestParam(defaultValue = "0") int page) {
		Page<books> list = service.findBookByCriteria(criter.getAuthorid(), criter.getPublishersid(), criter.getCategoriesid(), criter.getBooknamekeyword(), page, page_size);
		model.addAttribute("criteria", new filterCriteria());
		model.addAttribute("categories", cDAO.findAll());
		model.addAttribute("authors", authorsDAO.findAll());
		model.addAttribute("publishers", pDao.findAll());
		if(list.isEmpty()) {
			model.addAttribute("message", "Không tìm thấy cuốn sách nào");
		}
		model.addAttribute("items", list);
		return "cart/main";
	}
}

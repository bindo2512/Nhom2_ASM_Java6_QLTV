package com.controller.home;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;	
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.bookDAO;
import com.dao.categoriesDAO;
import com.dao.publishersDAO;
import com.dao.retailDAO;
import com.entity.accounts;
import com.entity.books;
import com.entity.commentData;
import com.entity.comments;
import com.entity.details;
import com.entity.filterCriteria;
import com.entity.history;
import com.entity.retails;
import com.entity.recordEntity.loginRecord;
import com.service.accountService;
import com.service.bookService;
import com.service.emailService;
import com.service.historyService;
import com.service.rentalService;

import io.getstream.chat.java.models.Message;
import javassist.compiler.ast.Keyword;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class productsController {
	
	@Autowired
	bookService service;
	
	@Autowired
	rentalService rentalService;

	@Autowired
	accountService aService;

	@Autowired
	emailService eService;

	@Autowired
	historyService hService;

	@Autowired
	categoriesDAO cDAO;

	@Autowired
	com.dao.authorsDAO authorsDAO;

	@Autowired
	publishersDAO pDao;

	@Autowired
	bookDAO bDAO;

	@Autowired
	retailDAO rDAO;

	static int page_size = 9;
	static int page_count = 0;
	static filterCriteria filterCriteria = new filterCriteria();
	

	@RequestMapping("/qltv/products")
	public String productsCtrl(Model model , @CurrentSecurityContext(expression = "authentication?.name") String username) {
		Page<books> list = service.findBookByCriteria(null, null, null, null, page_count, page_size);
		model.addAttribute("criteria", new filterCriteria());
		model.addAttribute("lastest5", service.findTop5Lastest());
		model.addAttribute("items", list);
		model.addAttribute("categories", cDAO.findAll());
		model.addAttribute("authors", authorsDAO.findAll());
		model.addAttribute("publishers", pDao.findAll());
		filterCriteria.clear();
		if (username.toString() != "anonymousUser") {
			new commentData().setUsername(username);
		}
		return "cart/main";
	}

	@GetMapping("/qltv/products/detail/{bookid}")
	public String productDetailController(Model model, @PathVariable("bookid") Integer id, @CurrentSecurityContext(expression = "authentication?.name") String username) {
		books item = service.findById(id);
		model.addAttribute("item", item);
		model.addAttribute("commentList", service.getAllCommentByBookid(id));
		comments comments = new comments();
		comments.setBooks(service.findById(id));
		model.addAttribute("commentForm", comments);
		return "cart/detail";
	}

	@PostMapping("/qltv/products/detail/{bookid}")
	public void postCommentController(Model model, @ModelAttribute("commentPost") comments comments) {

	}

	@RequestMapping("/qltv/products/pdf/{bookid}")
	public String pdfController(Model model, @PathVariable("bookid") Integer id , @CurrentSecurityContext(expression = "authentication?.name") String username) {
		if (username.toString() != "anonymousUser") {
			history history = new history();
			history.setAccounts(aService.findAccountById(username).get());
			history.setBooks(service.findById(id));
			Date date = new Date();
			history.setHistorydate(date);
			hService.save(history);
			books item = service.findById(id);
			model.addAttribute("item", item);
			return "cart/pdf";
		} else {
			books item = service.findById(id);
			model.addAttribute("item", item);
			return "cart/pdf";
		}

	}

	@RequestMapping("/user/cart")
	public String cartCtrl(Model model, @CurrentSecurityContext(expression = "authentication?.name") String username) {
		model.addAttribute("user", aService.findAccountById(username).get());
		return "cart/cart";
	}

	@RequestMapping("/user/rental")
	public String rentalCtrl(@PathVariable("retailid") Integer id, Model model) {
		model.addAttribute("rental", rentalService.findById(id));
		return "cart/checkout";
	}

	@RequestMapping("/user/rental/detail/{retailid}")
	public String rentalDetailCtrl(@PathVariable("retailid") Integer id, Model model) throws Exception {
		retails retails = rentalService.findRetailsById(id);
		List<details> details = rentalService.findById(id);
		eService.sendRentailVerification(retails, details);
		model.addAttribute("rental", retails);
		model.addAttribute("rdetail", details);
		return "cart/checkoutdetail";
	}

	@RequestMapping("/user/management")
	public String rentalHistoryCtrl() {
		return "userManagement/userManagement";
	}

	@RequestMapping("/user/chat")
	public String chatCtrl() {
		return "messaging/messaging";
	}
	


	@RequestMapping("/qltv/products/search")
	public String searchCtrl(Model model, @ModelAttribute("criteria") filterCriteria criter, @RequestParam(defaultValue = "0") int page, @RequestParam(name = "authorid", required = false) Integer authorid, @RequestParam(name = "publishersid", required = false) Integer publisherid, @RequestParam(name = "categoriesid", required = false) Integer categoryid, @Param("keyword") String keyword) {
		if(criter.getAuthorid() != null) {
			filterCriteria.setAuthorid(criter.getAuthorid());		
		}
		if (!criter.getBooknamekeyword().isEmpty()) {
			filterCriteria.setBooknamekeyword(criter.getBooknamekeyword());
		}
		if (criter.getCategoriesid() != null) {			
			filterCriteria.setCategoriesid(criter.getCategoriesid());
		}
		if (criter.getPublishersid() != null) {
			filterCriteria.setPublishersid(criter.getPublishersid());
		}
		if (criter.getAuthorid() == null && criter.getBooknamekeyword().isEmpty() && criter.getCategoriesid() == null && criter.getPublishersid() == null) {
			filterCriteria.clear();
		}
		Page<books> list = service.findBookByCriteria(filterCriteria.getAuthorid(), filterCriteria.getPublishersid(), filterCriteria.getCategoriesid(), filterCriteria.getBooknamekeyword(), page, page_size);
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

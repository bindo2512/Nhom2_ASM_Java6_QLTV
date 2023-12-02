package com.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.accountDAO;
import com.entity.accounts;
import com.entity.filterCriteria;
import com.entity.forgotPassword;
import com.entity.recordEntity.loginRecord;
import com.service.accountService;
import com.service.emailService;
import com.service.userService;
import com.service.verifyService;

import net.bytebuddy.utility.RandomString;


@Controller
public class loginController {

	static String code;
	static Boolean isNormal = false;
	static forgotPassword fp = new forgotPassword();

	@Autowired
	accountService aService;

	@Autowired
	userService service;

	@Autowired
	verifyService vservice;

	@Autowired
	emailService eservice;

	@Autowired BCryptPasswordEncoder pe;

	@RequestMapping("/qltv/register")
	public String registerCtrl(Model model) {
		model.addAttribute("user", new accounts());
		return "forgot_and_login/register";
	}

	@RequestMapping("/qltv/forgot")
	public String forgotCtrl(Model model) {
		model.addAttribute("forgot", new accounts());
		return "forgot_and_login/forgot";
	}

	@RequestMapping("/qltv/login/form")
	public String loginCtrl(Model model) {
		return "forgot_and_login/login";
	}
	
	@RequestMapping("/qltv/login/success")
	public String loginCtrlSuccess(Model model) {
		isNormal = true;
		return "redirect:/qltv/products";
	}

	@RequestMapping("/oauth2/login/success")
	public String oauth2Login(OAuth2AuthenticationToken oauth2) {
		service.loginFormOAuth2(oauth2);
		return "forward:/qltv/login/success";
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
		if (isNormal) {
			aService.updateLogout();
			isNormal = false;
		}
		model.addAttribute("message", "Đăng xuất thành công");
		return "forgot_and_login/login";
	}

	@PostMapping("/qltv/register/save")
	public String save(@ModelAttribute("user") accounts accounts, Model model) throws Exception {
		accounts.setPassword(pe.encode(accounts.getPassword()));;
		accounts.setCreatedate(new java.sql.Date(new Date().getTime()));
		accounts.setIsactive(false);
		accounts.setIsadmin(false);
		accounts.setVerification(RandomString.make(64));
		eservice.sendVerificationEmail(accounts);
		aService.createNewAccount(accounts);
		model.addAttribute("message", "Đăng ký thành công, vui lòng check email để xác thực tài khoản");
		return "forgot_and_login/login";
	}


	@PostMapping("/qltv/forgot/submit")
	public String forgotSubmit(@ModelAttribute("forgot") accounts accounts, Model model) throws Exception {
		boolean verify = vservice.verifyForgotPassword(accounts);
		if (verify == true) {

			fp.setForgotToken(RandomString.make(8));
			code = fp.getForgotToken();
			fp.setTokenTime(LocalDateTime.now());
			fp.setUsername(accounts.getUsername());
			eservice.sendForgotPasswordVerificationEmail(accounts, fp);
			if (fp.getTokenTime() != null && Duration.between(fp.getTokenTime(), LocalDateTime.now()).toMinutes() < 5) {
				model.addAttribute("forgot", new forgotPassword());
				model.addAttribute("message", "Đã gửi xác nhận quên mật khẩu, vui lòng checkmail để xác nhận");
				return "forgot_and_login/forgot_verification";
			} else {
				fp.setForgotToken(null);
				code = null;	
				model.addAttribute("message", "Mã code xác nhận đã hết hạn");
				return "forgot_and_login/forgot";
			}
		} else {
			model.addAttribute("message", "Email hoặc tên tài khoản của bạn không chính xác");
			return "forgot_and_login/forgot";
		}
		
	}

	@PostMapping("/qltv/forgot/changepass")
	public String forgotChangepass(@ModelAttribute("forgot") forgotPassword forgotPassword, Model model) throws Exception {
		if (forgotPassword.getEnterForgotToken().equals(fp.getForgotToken())) {
			model.addAttribute("newUserPassword", new accounts());
			return "forgot_and_login/forgot_newpassword";
		} else {
			model.addAttribute("message", "Bạn đã nhập sai mã xác nhận");
			return "forgot_and_login/forgot_verification";
		}
	}

	@PostMapping("/qltv/forgot/changepass/submit")
	public String forgotChangepassSubmit(@ModelAttribute("newUserPassword") accounts accounts, Model model) throws Exception {
		boolean verify = vservice.verifyNotSamePassword(fp.getUsername(), accounts);
		if (verify) {
			model.addAttribute("message", "Khôi phục mật khẩu thành công");
			return "forgot_and_login/login";
		} else {
			model.addAttribute("message", "Khôi phục mật khẩu thất bại");
			return "forgot_and_login/forgot_newpassword";
		}
	}

	@GetMapping("/qltv/verify")
	public String verifyAccount(@Param("code") String code, Model model) {

		boolean verify = vservice.verifyRegistration(code);
		System.out.println(code);
		model.addAttribute("message", verify? "Xác thực thành công" : "Xác thực thất bại");
		return "forgot_and_login/login";
	}

	@GetMapping("/qltv/rental/verify")
	public String verifyRental(@Param("code") String code, Model model) {
		boolean verify = vservice.verifyCheckout(code);
		model.addAttribute("message", verify? "Xác thực thành công": "Xác thực thất bại");
		return "cart/main";
	}
}

package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mainController {
@RequestMapping("/qltv") 
public String mainCtrl() {
	return "forgot_and_login/landingPage";
}
}

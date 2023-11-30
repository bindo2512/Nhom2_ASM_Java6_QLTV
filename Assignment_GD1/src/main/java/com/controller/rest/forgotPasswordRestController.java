package com.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.forgotPassword;

@RestController
@RequestMapping("/rest/forgot")
public class forgotPasswordRestController {
    
    @GetMapping()
    public String resetToken() {
        new forgotPassword().resetToken();
        return "Mã xác thực đã hết hạn";
    }
}
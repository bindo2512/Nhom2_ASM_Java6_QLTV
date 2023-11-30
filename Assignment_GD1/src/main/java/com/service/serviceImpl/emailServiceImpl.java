package com.service.serviceImpl;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.dao.accountDAO;
import com.entity.accounts;
import com.entity.forgotPassword;
import com.service.emailService;

@Transactional
@Service
public class emailServiceImpl implements emailService {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    accountDAO aDao;

    @Override
    public void sendVerificationEmail(accounts accounts) throws UnsupportedEncodingException, MessagingException {
        String subject = "Vui lòng xác thực đăng ký";
        String sender = "Book it NOW! Developers Team";
        String mailContent = "Thân gửi " + accounts.getAccountdetail().getFullname();
        mailContent += "<p>Nhấp vào đường dẫn sau để kích hoạt tài khoản</p>";
        String siteUrl = "http://localhost:8080/qltv/verify?code=" + accounts.getVerification();
        mailContent += "<h3><a href=\"" + siteUrl + "\">Xác thực</a></h3>";
        mailContent += "<p>Cảm ơn vì đã sử dụng dịch vụ của chúng tôi</p>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("eazy97889@gmail.com", sender);
        helper.setTo(accounts.getAccountdetail().getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent, true);
        mailSender.send(message);
    }

    @Override
    public void sendForgotPasswordVerificationEmail(accounts accounts,forgotPassword fp) throws Exception {
        String subject = "Mail xác thực quên mật khẩu";
        String sender = "Book it NOW! Developers Team";
        String mailContent = "Thân gửi " + aDao.findById(accounts.getUsername()).get().getAccountdetail().getFullname() + " !";
        mailContent += "<p>Mã xác nhận của bạn là</p>"; 
        mailContent += "<h1>" + fp.getForgotToken() + "</h1>";
        mailContent += "<p>Cảm ơn vì đã sử dụng dịch vụ của chúng tôi</p>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("eazy97889@gmail.com", sender);
        helper.setTo(aDao.findById(accounts.getUsername()).get().getAccountdetail().getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent, true);
        mailSender.send(message);
    }
    
}

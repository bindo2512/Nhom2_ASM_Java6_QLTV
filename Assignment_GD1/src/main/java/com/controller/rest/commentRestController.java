package com.controller.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.comments;
import com.service.accountService;
import com.service.authorsService;
import com.service.bookService;
import com.service.commentService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/rest/comments")
public class commentRestController {

    @Autowired
    commentService service;

    @Autowired
    bookService bService;

    @Autowired
    accountService aService;

    @GetMapping
    public List<comments> getAll() {
        return service.getAll();
    }

    @GetMapping("/{bookid}")
    public List<comments> getCommentsByBookid(@PathVariable("bookid") Integer id) {
        return bService.getAllCommentByBookid(id);
    }

    @PostMapping("/{bookid}")
    public comments postMethodName(@PathVariable("bookid") Integer id, @CurrentSecurityContext(expression = "authentication?.name") String username, @RequestBody comments comments) {
        comments.setAccounts(aService.findAccountById(username).get());
        comments.setBooks(bService.findById(id));
        comments.setCommentdate(new Date());
        return service.saveComment(comments);
    }

    @DeleteMapping("/{commentid}")
    public void deleteCommentById(@PathVariable("commentid") Integer id) {
        service.delete(id);
    }
    
    
}

package com.controller.rest;

import java.io.File;
import java.net.MalformedURLException;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.service.uploadService;

@CrossOrigin("*")
@RestController
public class uploadRestController {
    @Autowired
    uploadService uploadService;

    @PostMapping("/rest/upload/{folder}")
    public JsonNode upload(@PathParam("file") MultipartFile file, @PathVariable("folder") String folder) throws MalformedURLException {
        File saveFile = uploadService.saveFilePDF(file, folder);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("name", saveFile.getName());
        node.put("size", saveFile.length());
        return node;
    }

    @PostMapping("/rest/upload/image/{folder}")
    public JsonNode uploadImage(@PathParam("file") MultipartFile file, @PathVariable("folder") String folder) throws MalformedURLException {
        File saveFile = uploadService.saveFileIMG(file, folder);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("name", saveFile.getName());
        node.put("size", saveFile.length());
        return node;
    }
}

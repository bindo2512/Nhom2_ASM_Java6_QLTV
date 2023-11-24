package com.service;

import java.io.File;
import java.net.MalformedURLException;

import org.springframework.web.multipart.MultipartFile;

public interface uploadService {
    File saveFilePDF(MultipartFile file, String folder) throws MalformedURLException;
    File saveFileIMG(MultipartFile file, String folder) throws MalformedURLException;
}

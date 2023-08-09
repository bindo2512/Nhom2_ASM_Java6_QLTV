package com.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface uploadService {
    File saveFilePDF(MultipartFile file, String folder);
    File saveFileIMG(MultipartFile file, String folder);
}

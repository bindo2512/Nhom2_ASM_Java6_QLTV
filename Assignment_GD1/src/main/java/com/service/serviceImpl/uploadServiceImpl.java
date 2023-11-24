package com.service.serviceImpl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;

import org.apache.jasper.tagplugins.jstl.core.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.service.uploadService;

@Service
public class uploadServiceImpl implements uploadService{
    
    @Autowired
    ServletContext app;

    @Override
    public File saveFilePDF(MultipartFile file, String folder) throws MalformedURLException {
        URL pdfUrl = app.getResource("/assest/" + folder);
        File dir = new File(pdfUrl.toString());
        if (!dir.exists()) {
            dir.mkdir();
        }
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String name = Integer.toHexString(fileName.hashCode()) + fileName.substring(fileName.lastIndexOf("."));
        try {
            File saveFile = new File(dir, name);
            file.transferTo(saveFile);
            System.out.println(saveFile.getAbsolutePath());
            return saveFile;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException();
        }

    }

    @Override
    public File saveFileIMG(MultipartFile file, String folder) throws MalformedURLException {
        URL imgUrl = app.getResource("/assest/image/" + folder);
        File dir = new File(app.getRealPath("/assest/image/" + folder));
        if (!dir.exists()) {
            dir.mkdir();
        }
        String fileName = file.getOriginalFilename();
        try {
            File saveFile = new File(dir, fileName);
            System.out.println(app.getRealPath("/assest/image"));
            file.transferTo(saveFile);
            System.out.println(saveFile.getAbsolutePath());
            return saveFile;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException();
        }

    }
    
}

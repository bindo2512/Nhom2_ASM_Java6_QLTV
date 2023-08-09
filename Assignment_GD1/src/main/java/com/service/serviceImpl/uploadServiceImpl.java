package com.service.serviceImpl;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.service.uploadService;

@Service
public class uploadServiceImpl implements uploadService{
    
    @Autowired
    ServletContext app;

    @Override
    public File saveFilePDF(MultipartFile file, String folder) {
        File dir = new File(app.getRealPath("/assest/" + folder));
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
    public File saveFileIMG(MultipartFile file, String folder) {
        File dir = new File(app.getRealPath("/assest/image/" + folder));
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
    
}

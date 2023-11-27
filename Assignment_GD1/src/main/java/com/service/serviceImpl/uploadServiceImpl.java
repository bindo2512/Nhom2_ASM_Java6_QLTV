package com.service.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletContext;

import org.apache.jasper.tagplugins.jstl.core.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.service.uploadService;

@Service
public class uploadServiceImpl implements uploadService{
    
    @Autowired
    ServletContext app;
    

    @Override
    public File saveFilePDF(MultipartFile file, String folder) throws IOException {
        String Path_Dir = "C:/Users/LAPTOP/eclipse-workspace/Nhom2_ASM_Java6_QLTV/Assignment_GD1/src/main/resources/static/assest/" + folder;
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String name = Integer.toHexString(fileName.hashCode()) + fileName.substring(fileName.lastIndexOf("."));
        try {
            File saveFile = new File(Path_Dir, name);
            Files.copy(file.getInputStream(), Paths.get(Path_Dir + File.separator + name), StandardCopyOption.REPLACE_EXISTING);
            return saveFile;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException();
        }

    }

    @Override
    public File saveFileIMG(MultipartFile file, String folder) throws IOException {
        String Path_Dir = "C:/Users/LAPTOP/eclipse-workspace/Nhom2_ASM_Java6_QLTV/Assignment_GD1/src/main/resources/static/assest/image/" + folder;
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String name = Integer.toHexString(fileName.hashCode()) + fileName.substring(fileName.lastIndexOf("."));
        try {
            File saveFile = new File(Path_Dir, name);
            Files.copy(file.getInputStream(), Paths.get(Path_Dir + File.separator + name), StandardCopyOption.REPLACE_EXISTING);
            return saveFile;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException();
        }

    }
    
}

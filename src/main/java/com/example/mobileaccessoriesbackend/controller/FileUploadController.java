package com.example.mobileaccessoriesbackend.controller;

import com.example.mobileaccessoriesbackend.services.interfaces.IFileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    private IFileService fileService;

    public FileUploadController(IFileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
        fileService.uploadFile(file);
    }
}

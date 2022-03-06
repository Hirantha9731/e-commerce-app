package com.example.mobileaccessoriesbackend.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {

    void uploadFile(MultipartFile file) throws IllegalStateException , IOException;
}

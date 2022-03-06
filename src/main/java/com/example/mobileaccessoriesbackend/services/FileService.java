package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.services.interfaces.IFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@Service
public class FileService implements IFileService {

    @Override
    public void uploadFile(MultipartFile file) throws IllegalStateException , IOException {

        file.transferTo(new File("E:\\SpringBoot-Projects\\Image_upload_test_project\\Uploads\\" + file.getOriginalFilename()));
    }

}

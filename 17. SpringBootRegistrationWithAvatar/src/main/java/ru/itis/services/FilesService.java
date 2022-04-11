package ru.itis.services;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FilesService {
    void uploadAvatar(MultipartFile multipartFile, String description, String login);
    ResponseEntity<InputStreamResource> getFileByStorageName(String storageFileName);
}

package ru.itis.services;

import org.springframework.web.multipart.MultipartFile;

public interface FilesService {
    void upload(MultipartFile file, String description);
}

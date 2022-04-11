package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.services.FilesService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FilesServiceImpl implements FilesService {
    @Value("${files.storage.path}")
    private String storagePath;

    @Override
    public void upload(MultipartFile file, String description) {
        try {
            Files.copy(file.getInputStream(), Paths.get(storagePath, file.getOriginalFilename()));
        } catch(IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

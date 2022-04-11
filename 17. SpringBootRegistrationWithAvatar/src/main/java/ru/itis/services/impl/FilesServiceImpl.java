package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.models.Account;
import ru.itis.models.FileInfo;
import ru.itis.repositories.AccountsRepository;
import ru.itis.repositories.FileInfoRepository;
import ru.itis.services.FilesService;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FilesServiceImpl implements FilesService {
    @Value("${files.storage.path}")
    private String storagePath;

    @Value("${files.url}")
    private String filesUrl;

    private final FileInfoRepository fileInfoRepository;
    private final AccountsRepository accountsRepository;

    @Override
    @Transactional
    public void uploadAvatar(MultipartFile multipartFile, String description, String login) {
        try {
            String extension = Objects.requireNonNull(multipartFile.getOriginalFilename())
                    .substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            String storageFileName = UUID.randomUUID() + extension;

            FileInfo fileInfo = FileInfo.builder()
                    .type(multipartFile.getContentType())
                    .originalFileName(multipartFile.getOriginalFilename())
                    .description(description)
                    .storageFileName(storageFileName)
                    .size(multipartFile.getSize())
                    .build();
            Files.copy(multipartFile.getInputStream(),
                    Paths.get(storagePath, fileInfo.getStorageFileName()));

            fileInfoRepository.save(fileInfo);

            Account account = accountsRepository.findByLogin(login).orElseThrow(IllegalArgumentException::new);
            account.setAvatarLink(filesUrl + fileInfo.getStorageFileName());
            accountsRepository.save(account);

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public ResponseEntity<InputStreamResource> getFileByStorageName(String storageFileName) {
        try {
            FileInfo fileInfo = fileInfoRepository.findByStorageFileName(storageFileName).orElseThrow(IllegalArgumentException::new);

            InputStreamResource inputStreamResource =
                    new InputStreamResource(new FileInputStream(storagePath + "\\" + fileInfo.getStorageFileName()));

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "filename=\"" + fileInfo.getOriginalFileName() + "\"");
            headers.add("File-Description", fileInfo.getDescription());

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(fileInfo.getSize())
                    .contentType(MediaType.valueOf(fileInfo.getType()))
                    .body(inputStreamResource);

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}

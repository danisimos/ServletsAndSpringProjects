package ru.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.model.FileInfo;
import ru.itis.repositories.FileInfoRepository;
import ru.itis.services.FilesService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class FilesServiceImpl implements FilesService {
    FileInfoRepository fileInfoRepository;

    @Autowired
    public FilesServiceImpl(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }

    @Value("${storage.path}")
    private String storagePath;

    @Override
    public void upload(String originalFileName, Long size, String mimeType, InputStream inputStream) {
        FileInfo fileInfo = FileInfo.builder()
                .originalFileName(originalFileName)
                .storageFileName(UUID.randomUUID().toString())
                .size(size)
                .mimeType(mimeType)
                .build();

        fileInfoRepository.save(fileInfo);

        try {
            Files.copy(inputStream, Paths.get(storagePath + fileInfo.getStorageFileName() + "." + mimeType.split("/")[1]));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public FileInfo getFileInfoByFileName(String fileName) {
        Optional<FileInfo> fileInfo = fileInfoRepository.findByFileName(fileName);

        return fileInfo.orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void download(FileInfo fileInfo, OutputStream outputStream) {
        try {
            Files.copy(Paths.get(storagePath
                        + fileInfo.getStorageFileName()
                        + "."
                        + fileInfo.getMimeType().split("/")[1])
                    , outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }
}

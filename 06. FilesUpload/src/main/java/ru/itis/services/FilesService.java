package ru.itis.services;

import ru.itis.model.FileInfo;

import java.io.InputStream;
import java.io.OutputStream;

public interface FilesService {
    void upload(String originalFileName, Long size, String mimeType, InputStream inputStream);
    FileInfo getFileInfoByFileName(String fileName);
    void download(FileInfo fileInfo, OutputStream outputStream);
}

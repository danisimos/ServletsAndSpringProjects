package ru.itis.repositories;

import ru.itis.model.FileInfo;

import java.util.Optional;

public interface FileInfoRepository {
    FileInfo save(FileInfo fileInfo);
    Optional<FileInfo> findByFileName(String fileName);
}

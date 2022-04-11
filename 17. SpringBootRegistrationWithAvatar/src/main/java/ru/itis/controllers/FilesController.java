package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.services.FilesService;

@Controller
@RequiredArgsConstructor
@RequestMapping("files")
public class FilesController {
    private final FilesService filesService;

    @PostMapping("upload/{login}")
    public void uploadAvatar(@PathVariable(name = "login") String login,
                             @RequestParam("file")MultipartFile multipartFile,
                             @RequestParam("description") String description) {
        filesService.uploadAvatar(multipartFile, description, login);
    }

    @GetMapping("/{storageFileName}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable(name = "storageFileName") String storageFileName) {
        return filesService.getFileByStorageName(storageFileName);
    }
}

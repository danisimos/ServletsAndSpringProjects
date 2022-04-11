package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.services.FilesService;

import java.util.Arrays;

@Controller
@RequestMapping("/files")
@RequiredArgsConstructor
public class FilesUploadController {
    private final FilesService filesService;

    @GetMapping
    public String getFilesUploadPage() {
        return "files_upload";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("files") MultipartFile[] files, @RequestParam("description") String description) {
        Arrays.stream(files).forEach(f -> filesService.upload(f, description));
        return "redirect:/files";
    }
}

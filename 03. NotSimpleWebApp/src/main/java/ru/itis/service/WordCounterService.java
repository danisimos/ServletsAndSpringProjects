package ru.itis.service;

import ru.itis.repositories.WordsRepository;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WordCounterService {
    WordsRepository wordsRepository;

    public WordCounterService(WordsRepository wordsRepository) {
        this.wordsRepository = wordsRepository;
    }

    public void saveAllFiles(String path) {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path));) {
            directoryStream.forEach(p -> saveFile(p));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public void saveFile(Path path) {
        Map<String, Integer> map = new HashMap<>();

        try {
            Files.lines(path)
                    .flatMap(line -> Arrays.stream(line.split("[ ,.;:?!\"—\\u00A0\\u002D]+")))
                    .filter(s -> !s.equals("") & !s.equals(" "))
                    .forEach(s -> {
                        s = s.toLowerCase();
                        if(map.containsKey(s)) {
                            map.put(s, map.get(s) + 1);
                        } else {
                            map.put(s, 1);
                        }
                    });
            String fileName = path.getFileName().toString();
            wordsRepository.saveFile(map, fileName.substring(0, fileName.indexOf(".txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Map<String, Integer>> getFilesTopWords() {
        List<String> filesNames = wordsRepository.getFilesNames();
        Map<String, Map<String, Integer>> result = new HashMap<>();

        for(String fileName: filesNames) {
            Map<String, Integer> wordsAmount = wordsRepository.getTopWords(fileName);
            result.put(fileName, wordsAmount);
        }

        return result;
    }
}

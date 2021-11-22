package ru.itis.repositories;

import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;

public interface WordsRepository {
    public void saveFile(Map<String, Integer> map, String fileName);
    public List<String> getFilesNames();
    public Map<String, Integer> getTopWords(String fileName);
}

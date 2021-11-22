package ru.itis.service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WordCounter {
    public String getHtmlResonse(String path) {
        StringBuilder sb = new StringBuilder("<table border=\"1\">");
        sb.append("<tr>" +
                "<th>File Name</th>" +
                "<th>1</th>" +
                "<th>2</th>" +
                "<th>3</th>" +
                "<th>4</th>" +
                "<th>5</th>" +
                "<th>6</th>" +
                "<th>7</th>" +
                "<th>8</th>" +
                "<th>9</th>" +
                "<th>10</th>" +
                "</tr>");

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path))) {
            directoryStream.forEach(p ->sb.append(fileTable(p)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sb.append("</table>");
        return sb.toString();
    }

    public String fileTable(Path path) {
        Map<String, Integer> map = new TreeMap<>();

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
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = path.getFileName().toString();
        StringBuilder htmlTableRow = new StringBuilder("<tr><th>" + fileName + "</th>");

        map.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .limit(10)
                .forEach(e -> htmlTableRow.append("<th>" + e.getKey() + ": " + e.getValue() + "</th>"));
        htmlTableRow.append("</tr>");

        return htmlTableRow.toString();
    }
}

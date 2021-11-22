package ru.itis.repositories.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.repositories.WordsRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WordsRepositoryJdbcTemplateImpl implements WordsRepository {
    private  final JdbcTemplate jdbcTemplate;
    private static final String SQL_INSERT_INTO = "insert into words_amount(word, amount, file_name) values (?, ?, ?)";
    private static final String SQL_SELECT_FILENAME = "select distinct file_name from words_amount";
    private static final String SQL_SELECT_TOP = "select word, amount from words_amount where file_name = ? order by amount desc limit 10";

    private final RowMapper<String> fileNameRowMapper = ((rs, rowNum) -> rs.getString("file_name"));

    public WordsRepositoryJdbcTemplateImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveFile(Map<String, Integer> map, String fileName) {
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            jdbcTemplate.update(SQL_INSERT_INTO, entry.getKey(), entry.getValue(), fileName);
        }
    }

    @Override
    public List<String> getFilesNames() {
        return jdbcTemplate.query(SQL_SELECT_FILENAME, fileNameRowMapper);
    }

    @Override
    public Map<String, Integer> getTopWords(String fileName) {
        Map<String, Integer> map = jdbcTemplate.query(SQL_SELECT_TOP, (ResultSet rs) -> {
            LinkedHashMap<String,Integer> results = new LinkedHashMap<>();
            while (rs.next()) {
                results.put(rs.getString("word"), rs.getInt("amount"));
            }
            return results;
        }, fileName);

        return map;
    }
}

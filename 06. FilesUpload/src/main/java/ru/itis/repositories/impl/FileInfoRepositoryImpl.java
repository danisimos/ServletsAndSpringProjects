package ru.itis.repositories.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.model.FileInfo;
import ru.itis.repositories.FileInfoRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.Optional;

@Repository
public class FileInfoRepositoryImpl implements FileInfoRepository {
    private final JdbcTemplate jdbcTemplate;

    private final static String SQL_INSERT = "insert into file_info (original_file_name, storage_file_name, size, type) VALUES (?, ?, ?, ?)";
    private final static String SQL_SELECT_BY_FILE_NAME = "select * from file_info where storage_file_name = ?";
    private final String SQL_UPDATE_BY_ID = "update file_info set original_file_name = ?, storage_file_name = ?, size = ?, type = ? where id = ?";

    private final RowMapper<FileInfo> fileInfoRowMapper = (row, rowNumber) -> FileInfo.builder()
            .id(row.getInt("id"))
            .originalFileName(row.getString("original_file_name"))
            .storageFileName(row.getString("storage_file_name"))
            .size((long) row.getInt("size"))
            .mimeType(row.getString("type"))
            .build();

    public FileInfoRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public FileInfo save(FileInfo fileInfo) {
        if(fileInfo.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                statement.setString(1, fileInfo.getOriginalFileName());
                statement.setString(2, fileInfo.getStorageFileName());
                statement.setInt(3, fileInfo.getSize().intValue());
                statement.setString(4, fileInfo.getMimeType());

                return statement;
            }, keyHolder);
            fileInfo.setId(keyHolder.getKey().intValue());
        } else {
            jdbcTemplate.update(SQL_UPDATE_BY_ID,
                    fileInfo.getOriginalFileName(),
                    fileInfo.getStorageFileName(),
                    fileInfo.getSize(),
                    fileInfo.getMimeType(),
                    fileInfo.getId()
            );
        }
        return fileInfo;
    }

    @Override
    public Optional<FileInfo> findByFileName(String fileName) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_FILE_NAME, fileInfoRowMapper, fileName));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}

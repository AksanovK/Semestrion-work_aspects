package ru.itis.javalab.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class MethodsRepositoryImpl implements MethodsRepository {

    public MethodsRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_UPDATE_COUNT = "UPDATE methods meh set count = ? where meh.name = ?";

    //language=SQL
    private static final String SQL_SAVE_METHOD = "insert into methods(name, count) values (?, ?)";

    //language=SQL
    private static final String SQL_DELETE_METHOD = "DELETE from methods";

    public MethodsRepositoryImpl() {
    }

    @Override
    public void enlargeCount(String name, int count) {
        jdbcTemplate.update(SQL_UPDATE_COUNT, name, count);
    }

    @Override
    public void save(String name, int count) {
        jdbcTemplate.update(SQL_SAVE_METHOD, name, count);
    }

    @Override
    public void delete() {
        jdbcTemplate.update(SQL_DELETE_METHOD);
    }
}

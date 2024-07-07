package project.learn.rluzinov.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import project.learn.rluzinov.models.BookModel;

import java.util.List;
@Component

public class BookDao {
    private JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<BookModel> index(){
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(BookModel.class));
    }
}

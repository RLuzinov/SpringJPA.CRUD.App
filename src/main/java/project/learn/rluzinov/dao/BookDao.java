package project.learn.rluzinov.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import project.learn.rluzinov.models.Book;

import java.util.List;
@Component

public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }
    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id = ? ", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);

    }
    public void save(Book book){
        jdbcTemplate.update("INSERT INTO Book(age, name, author) VALUES (?,?,?)", book.getAge(), book.getName(), book.getAuthor());
    }
}

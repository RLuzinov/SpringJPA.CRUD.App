package project.learn.rluzinov.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import project.learn.rluzinov.models.Book;
import project.learn.rluzinov.models.People;

import java.util.List;
import java.util.Optional;

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
        jdbcTemplate.update("INSERT INTO Book(age, name, author) VALUES (?,?,?)",
                book.getAge(), book.getName(), book.getAuthor());
    }
    public void update(int id, Book updateBook) {
        jdbcTemplate.update("UPDATE Book SET age = ?, name= ?, author = ? WHERE id = ?",
                updateBook.getAge(), updateBook.getName(), updateBook.getAuthor(), id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE id =?", id);
    }

    public Optional<People> getBookOwner(int id){
        return jdbcTemplate.query("SELECT People.* FROM Book JOIN People ON Book.people_id = People.id" +
                        " WHERE Book.id =? ", new Object[]{id}, new BeanPropertyRowMapper<>(People.class)).stream().findAny();
    }
    public  void realise(int id){
        jdbcTemplate.update("UPDATE Book SET people_id = NULL WHERE id = ?", id);
    }
    public void assign (int id, People selectedPeople){
        jdbcTemplate.update("UPDATE Book SET people_id=? WHERE id=?", selectedPeople.getId(), id);
    }
}

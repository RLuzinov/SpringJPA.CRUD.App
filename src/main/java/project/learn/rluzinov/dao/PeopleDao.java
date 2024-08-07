package project.learn.rluzinov.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import project.learn.rluzinov.models.Book;
import project.learn.rluzinov.models.People;

import java.util.List;
import java.util.Optional;


@Component
public class PeopleDao {
    private final JdbcTemplate jdbcTemplate;

    public PeopleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<People> index(){
        return jdbcTemplate.query("SELECT  * FROM People", new BeanPropertyRowMapper<>(People.class)) ;
    }
    public People show(int id){
        return jdbcTemplate.query("SELECT * FROM  People WHERE id =?", new Object[]{id}, new BeanPropertyRowMapper<>(People.class))
        .stream().findAny().orElse(null);
    }
    public void save(People people){
        jdbcTemplate.update("INSERT INTO People(name, age, id_book) VALUES (?,?,?)",
                people.getName(), people.getAge(), people.getId_book() );
    }
    public void update(int id, People updatePeople){
        jdbcTemplate.update("UPDATE People SET name=?, age=?, id_book=? WHERE id = ?",
                updatePeople.getName(), updatePeople.getAge(), updatePeople.getId_book(), id);
    }
    public  void delete(int id){
        jdbcTemplate.update("DELETE FROM People WHERE id=?", id);
    }

    public List<Book> getBookByPeople(int id)
        {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}

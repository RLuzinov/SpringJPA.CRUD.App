package project.learn.rluzinov.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.learn.rluzinov.models.Book;
import project.learn.rluzinov.models.People;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor

@Component


public class BookDao {
    SessionFactory sessionFactory;
    @Transactional
    public List<Book> index(){
        Session session = sessionFactory.getCurrentSession();
        List<Book> book = session.createQuery("select b from  Book b", Book.class)
                .getResultList();

        return  book;
    }
    @Transactional
    public Book show(int id){
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        return  book;
    }
    @Transactional
    public void save(Book book){
        Session session = sessionFactory.getCurrentSession();
        session.save(book);
    }
    @Transactional
    public void update(int id, Book updateBook) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        book = updateBook;
        session.save(book);
    }
    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        session.delete(book);
    }

    public Optional<People> getBookOwner(int id){
        return  null;
    }
}

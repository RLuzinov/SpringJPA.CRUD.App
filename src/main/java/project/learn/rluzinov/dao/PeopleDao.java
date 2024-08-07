package project.learn.rluzinov.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PeopleDao {
    private SessionFactory sessionFactory;
    @Transactional
    public List<People> index(){
        Session session = sessionFactory.getCurrentSession();

        List<People> people = session.createQuery("select p from People p", People.class)
                .getResultList();
        return people;
    }
    @Transactional
    public People show(int id){
        Session session = sessionFactory.getCurrentSession();
        People people = session.get(People.class, id);
        return people;
    }
    @Transactional
    public void save(People people){
        Session session = sessionFactory.getCurrentSession();
        session.save(people);

    }
    @Transactional
    public void update(int id, People updatePeople){
        Session session = sessionFactory.getCurrentSession();
        People people = session.get(People.class, id);
        people = updatePeople;
        session.save(people);
    }
    @Transactional
    public  void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        People people = session.get(People.class, id);
        session.delete(people);
    }


}

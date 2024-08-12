package project.learn.rluzinov.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.learn.rluzinov.models.Person;

import java.util.List;

@AllArgsConstructor


@Component
public class PeopleDao {
    private SessionFactory sessionFactory;
    @Transactional
    public List<Person> index(){
        Session session = sessionFactory.getCurrentSession();

        List<Person> people = session.createQuery("select p from Person p", Person.class)
                .getResultList();
        return people;
    }
    @Transactional
    public Person show(int id){
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        return person;
    }
    @Transactional
    public void save(Person person){
        Session session = sessionFactory.getCurrentSession();
        session.save(person);

    }
    @Transactional
    public void update(int id, Person updatePerson){
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        person = updatePerson;
        session.save(person);
    }
    @Transactional
    public  void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        session.delete(person);
    }


}

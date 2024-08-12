package project.learn.rluzinov.srvices;

import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.learn.rluzinov.models.Book;
import project.learn.rluzinov.models.Person;
import project.learn.rluzinov.repositories.BookRepository;
import project.learn.rluzinov.repositories.PeopleRepository;

import java.util.*;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final BookRepository bookRepository;

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }
    public Person findById(int id){
        Optional<Person> people = peopleRepository.findById(id);
        return people.orElse(null);
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(Person updatePerson, int id){
        updatePerson.setId(id);
        peopleRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

    public Optional<Person> getPersonByName(String name){
    return peopleRepository.findPeopleByName(name);
    }

    public  List<Book> getBookByPersonId(int id){
        Optional<Person> people = peopleRepository.findById(id);
        if(people.isPresent()) {
            Hibernate.initialize(people.get().getBooks());
            people.get().getBooks().forEach(book -> {
                long diffInMillies = Math.abs(book.getBookAp().getTime() - new Date().getTime());
                if (diffInMillies > 864000000)
                    book.setExpired(true);
            });
            return people.get().getBooks();
        }
        else {
            return Collections.emptyList();

        }



    }

}

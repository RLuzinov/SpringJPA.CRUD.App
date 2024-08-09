package project.learn.rluzinov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.learn.rluzinov.models.Book;
import project.learn.rluzinov.models.People;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByOwner(People owner);
}

package project.learn.rluzinov.srvices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.learn.rluzinov.models.Book;
import project.learn.rluzinov.models.People;
import project.learn.rluzinov.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> findAll(){
       return bookRepository.findAll();
    }
    public Book findById(int id){
        Optional<Book> bookById = bookRepository.findById(id);
        return  bookById.orElse(null);
    }
    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }
    @Transactional
    public void update(Book updateBook, int id){
        updateBook.setId(id);
        bookRepository.save(updateBook);
    }
    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }

    public List<Book> findByOwner(People owner){
        return bookRepository.findByOwner(owner);
    }
}

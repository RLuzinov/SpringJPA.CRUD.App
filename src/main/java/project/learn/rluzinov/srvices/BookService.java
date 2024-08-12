package project.learn.rluzinov.srvices;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.learn.rluzinov.models.Book;
import project.learn.rluzinov.models.Person;
import project.learn.rluzinov.repositories.BookRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> findAll(boolean sortByYear){
       if (sortByYear){
           return bookRepository.findAll(Sort.by("age"));
       }
       else{
        return bookRepository.findAll();
        }
    }
    public Book findById(int id){
        Optional<Book> bookById = bookRepository.findById(id);
        return  bookById.orElse(null);
    }
    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }

    public List<Book> searchByName(String query){
        return bookRepository.findByNameStartingWith(query);
    }
    @Transactional
    public void update(Book updateBook, int id){
        Book bookToBeUpdated = bookRepository.findById(id).get();
        updateBook.setId(id);
        updateBook.setOwner(bookToBeUpdated.getOwner());
        bookRepository.save(updateBook);
    }
    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }

    public Person getBookOwner(int id){
        return bookRepository.findById(id).map(Book::getOwner).orElse(null);
    }

    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear){
    if(sortByYear){
        return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("age"))).getContent();
    }
    else {
        return bookRepository.findAll(PageRequest.of(page, booksPerPage )).getContent();
    }
    }

    @Transactional
    public void release(int id){
        bookRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(null);
                    book.setBookAp(null);
                }
        );

    }

    @Transactional
    public void assign(int id, Person selectPerson){
        bookRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(selectPerson);
                    book.setBookAp(new Date());
                }
        );

    }


}

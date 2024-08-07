package project.learn.rluzinov.controllers;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.learn.rluzinov.dao.BookDao;
import project.learn.rluzinov.dao.PeopleDao;
import project.learn.rluzinov.models.Book;
import project.learn.rluzinov.models.People;

import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDao bookDao;
    private  final PeopleDao peopleDao;
@Autowired
    public BookController(BookDao bookDao, PeopleDao peopleDao) {
        this.bookDao = bookDao;
    this.peopleDao = peopleDao;
}

    @GetMapping()
    public String index(Model model){
    model.addAttribute("book", bookDao.index());


    return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDao.show(id));
        Optional<People> bookOwner =  bookDao.getBookOwner(id);
        if(bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people", peopleDao.index());
        return "book/show";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
    return "book/new";
    }
    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/new";
        bookDao.save(book);
        return "redirect:/book";

    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
    model.addAttribute("book", bookDao.show(id));
    return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                             @PathVariable("id") int id){
    if(bindingResult.hasErrors())
        return "book/edit";
    bookDao.update(id, book);
    return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDao.delete(id);
        return "redirect:/book";
    }
    @PatchMapping("/{id}/realise")
    public String realise(@PathVariable("id") int id){
    bookDao.realise(id);
    return "redirect:/book/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("people") People selectedPeople){
    bookDao.assign(id, selectedPeople);
    return "redirect:/book/" + id;
    }

}

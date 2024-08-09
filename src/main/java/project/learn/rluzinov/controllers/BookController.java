package project.learn.rluzinov.controllers;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.learn.rluzinov.dao.BookDao;
import project.learn.rluzinov.dao.PeopleDao;
import project.learn.rluzinov.models.Book;
import project.learn.rluzinov.models.People;
import project.learn.rluzinov.srvices.BookService;

import java.util.Optional;
@AllArgsConstructor
@Controller
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;


    @GetMapping()
    public String index(Model model){
    model.addAttribute("book", bookService.findAll());


    return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookService.findById(id));
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
        bookService.save(book);
        return "redirect:/book";

    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
    model.addAttribute("book", bookService.findById(id));
    return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                             @PathVariable("id") int id){
    if(bindingResult.hasErrors())
        return "book/edit";
    bookService.update(book,id);
    return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookService.delete(id);
        return "redirect:/book";
    }
//    @PatchMapping("/{id}/realise")
//    public String realise(@PathVariable("id") int id){
//    bookDao.realise(id);
//    return "redirect:/book/" + id;
//    }

//    @PatchMapping("/{id}/assign")
//    public String assign(@PathVariable("id") int id, @ModelAttribute("people") People selectedPeople){
//    bookDao.assign(id, selectedPeople);
//    return "redirect:/book/" + id;
//    }

}

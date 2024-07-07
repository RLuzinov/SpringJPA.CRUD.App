package project.learn.rluzinov.controllers;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.learn.rluzinov.dao.BookDao;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDao bookDao;
@Autowired
    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping()
    public String index(Model model){
    model.addAttribute("book", bookDao.index());
    return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDao.show(id));
        return "book/show";
    }

}

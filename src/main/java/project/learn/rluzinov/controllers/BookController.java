package project.learn.rluzinov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.learn.rluzinov.dao.BookDao;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping()
    public String index(Model model){
    model.addAttribute("book", bookDao.index());
    return "book/index";
    }


}

package project.learn.rluzinov.controllers;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.learn.rluzinov.models.Book;
import project.learn.rluzinov.models.Person;
import project.learn.rluzinov.srvices.BookService;
import project.learn.rluzinov.srvices.PeopleService;

@AllArgsConstructor
@Controller
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final PeopleService peopleService;


    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(value = "sort_by_years", required = false) boolean sortByYear){
        if (page == null || booksPerPage == null)
            model.addAttribute("book", bookService.findAll(sortByYear)); // выдача всех книг
        else
            model.addAttribute("book", bookService.findWithPagination(page, booksPerPage, sortByYear));

        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookService.findById(id));

        Person bookOwner = bookService.getBookOwner(id);

        if (bookOwner != null)
            model.addAttribute("owner", bookOwner);
        else
            model.addAttribute("people", peopleService.findAll());

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
    @PatchMapping("/{id}/realise")
    public String release(@PathVariable("id") int id){
    bookService.release(id);
    return "redirect:/book/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson){
    bookService.assign(id, selectedPerson);
    return "redirect:/book/" + id;
    }

    @GetMapping("/search")
    public String searchPage(){
        return "book/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query ){
        model.addAttribute("book", bookService.searchByName(query));
        return "book/search";
    }

}

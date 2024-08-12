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
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final BookService bookService;
    @GetMapping
    public String index(Model model){
    model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("owner") Book book){
    model.addAttribute("people", peopleService.findById(id));
    model.addAttribute("books", peopleService.getBookByPersonId(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPeople(@ModelAttribute("people") Person person){
    return "people/new";
    }
    @PostMapping()
    public  String createPeople(@ModelAttribute("people") @Valid Person person,
                                BindingResult bindingResult){
    if(bindingResult.hasErrors())
        return "people/new";
    peopleService.save(person);
    return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id" )int id, Model model, @ModelAttribute("book") Book book ){
    model.addAttribute("people", peopleService.findById(id));
    return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("people") @Valid Person person,
                         BindingResult bindingResult){
    if(bindingResult.hasErrors())
        return "people/edit";
    peopleService.update(person,id);
    return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
    peopleService.delete(id);
    return "redirect:/people";
    }




}

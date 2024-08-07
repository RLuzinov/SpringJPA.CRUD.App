package project.learn.rluzinov.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.learn.rluzinov.dao.PeopleDao;
import project.learn.rluzinov.models.People;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleDao peopleDao;
@Autowired
    public PeopleController(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }
    @GetMapping
    public String index(Model model){
    model.addAttribute("people", peopleDao.index());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
    model.addAttribute("people", peopleDao.show(id));
    model.addAttribute("book", peopleDao.getBookByPeople(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPeople(@ModelAttribute("people") People people){
    return "people/new";
    }
    @PostMapping()
    public  String createPeople(@ModelAttribute("people") @Valid People people,
                                BindingResult bindingResult){
    if(bindingResult.hasErrors())
        return "people/new";
    peopleDao.save(people);
    return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id" )int id, Model model ){
    model.addAttribute("people", peopleDao.show(id));
    return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("people") @Valid People people,
                         BindingResult bindingResult){
    if(bindingResult.hasErrors())
        return "people/edit";
    peopleDao.update(id, people);
    return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
    peopleDao.delete(id);
    return "redirect:/people";
    }

}

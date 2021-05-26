package ru.yuri.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.yuri.DAO.PeopleDAO;
import ru.yuri.model.People;
import ru.yuri.services.PeopleService;

import java.security.Principal;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleDAO peopleDAO;

    public PeopleController( PeopleDAO peopleDAO) {

        this.peopleDAO = peopleDAO;
    }


    @GetMapping
    public String get2(Model model, Principal principal) {
        model.addAttribute("person", (People)peopleDAO.getUserByName(principal.getName()));
        return "/lk";
    }
}

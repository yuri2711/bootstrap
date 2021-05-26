package ru.yuri.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.yuri.model.People;
import ru.yuri.model.Role;
import ru.yuri.services.PeopleService;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private PeopleService service;
    private static final String rAdmin = "ROLE_ADMIN";
    private static final String rUser = "ROLE_USER";

    public AdminController(PeopleService service) {
        this.service = service;
    }

    @GetMapping()
    public String getAdminPage(Model model) {
        List<People>listUser = service.index();
        model.addAttribute("people", listUser);
        model.addAttribute("list", service.getAllRoles());
        model.addAttribute("personadd", new People());
        return "/admin";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String create(@ModelAttribute("person") People people) {

        getSetListRole(people);
        service.save(people);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/edit", method = {RequestMethod.PATCH, RequestMethod.POST})
    public String update(@ModelAttribute(value = "people") People people) {

        getSetListRole(people);
        service.update(people);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String delete(@PathVariable(value = "id") int id) {
        service.delete(id);
        return "redirect:/admin";
    }

    private Set<Role> getSetListRole(People people) {
        Set<Role> roleSet = new HashSet<>();

        if(people.getRoles().contains(rAdmin)){
            Role role = service.getSingleRole(rAdmin);
            roleSet.add(role);
        }

        if(people.getRoles().contains(rUser)) {
            Role role = service.getSingleRole(rUser);
            roleSet.add(role);
        }
        people.setRoles(roleSet);
        return roleSet;
    }
}

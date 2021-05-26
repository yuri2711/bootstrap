package ru.yuri.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.yuri.model.People;
import ru.yuri.model.Role;

import java.util.List;
import java.util.Set;

public interface PeopleService {
    List<People> index();
    People get(int id);
    void save(People people);
    void update(People people);
    void delete(int id);

    List<Role> getAllRoles();

    void saveRole(Role admin);

    Role getSingleRole(String rAdmin);
}

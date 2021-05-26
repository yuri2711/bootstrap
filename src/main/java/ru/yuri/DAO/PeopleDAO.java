package ru.yuri.DAO;

import org.springframework.security.core.userdetails.UserDetails;
import ru.yuri.model.People;
import ru.yuri.model.Role;

import java.util.List;
import java.util.Set;

public interface PeopleDAO {
    List<People> index();
    People get(int id);
    void save(People people);
    void update(People people);
    void delete(int id);
    UserDetails getUserByName(String username);

    List<Role> getAllRoles();

    void saveRole(Role role);

    Role getSingleRole(String rAdmin);
}

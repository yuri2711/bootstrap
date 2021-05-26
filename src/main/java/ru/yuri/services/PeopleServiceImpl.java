package ru.yuri.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yuri.DAO.PeopleDAO;
import ru.yuri.model.People;
import ru.yuri.model.Role;

import java.util.List;

@Service
@Transactional
public class PeopleServiceImpl implements PeopleService {

    private final PeopleDAO peopleDAO;

    @Autowired
    public PeopleServiceImpl(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<People> index() {
        return peopleDAO.index();
    }

    @Override
    @Transactional(readOnly = true)
    public People get(int id) {
        return peopleDAO.get(id);
    }

    @Override
    @Transactional
    public void save(People people) {
        peopleDAO.save(people);
    }

    @Override
    @Transactional
    public void update(People people) {
        peopleDAO.update(people);
    }

    @Override
    @Transactional
    public void delete(int id) {
        peopleDAO.delete(id);
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        for(Role r : peopleDAO.getAllRoles()) System.out.println(r.getRole());
        return peopleDAO.getAllRoles();
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        peopleDAO.saveRole(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getSingleRole(String rAdmin) {
        return peopleDAO.getSingleRole(rAdmin);
    }

}

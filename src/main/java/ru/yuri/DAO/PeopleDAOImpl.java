package ru.yuri.DAO;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ru.yuri.model.People;
import ru.yuri.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class PeopleDAOImpl implements PeopleDAO {

    @PersistenceContext
    EntityManager manager;

    @Override
    public List<People> index() {
        return manager.createQuery("select p from People p").getResultList();
    }

    @Override
    public People get(int id) {
        return manager.find(People.class, new Long(id));
    }

    @Override
    public void save(People people) {
        manager.persist(people);
    }

    @Override
    public void update(People people) {
        manager.merge(people);
    }

    @Override
    public void delete(int id) {
        manager.remove(get(id));
    }

    @Override
    public UserDetails getUserByName(String username) {

        if (index().size() == 0) {
            System.out.println("Список пользователей = " + index().size());
            Role role = new Role("ROLE_ADMIN");
            Role role1 = new Role("ROLE_USER");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            saveRole(role);
            saveRole(role1);
            People people = new People();
            people.setName("ADMIN");
            people.setPass("ADMIN");
            people.setAge(35);
            people.setRoles(roles);
            save(people);
        }

        return (UserDetails) manager.
                createQuery("select p from People p where p.name=:namePerson").setParameter("namePerson", username).getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {

        return manager.createQuery("select r from Role r ", Role.class).getResultList();
    }

    @Override
    public void saveRole(Role role) {
        manager.persist(role);
    }

    @Override
    public Role getSingleRole(String rAdmin) {
        return manager.createQuery("select r from Role r where r.role = :rolename", Role.class).setParameter("rolename",rAdmin).getSingleResult();
    }
}

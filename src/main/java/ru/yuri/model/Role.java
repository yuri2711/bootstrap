package ru.yuri.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name", unique = true)
    private String role;

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
//    private Set<People> list;

    public Role(String role) {
        this.role = role;
    }
    public Role() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
//
//    public Set<People> getList() {
//        return list;
//    }
//
//    public void setList(Set<People> list) {
//        this.list = list;
//    }

    @Override
    public String getAuthority() {
        return role;
    }
}

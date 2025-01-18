package com.example.projetodae.ejbs;

import com.example.projetodae.entities.TipoUser;
import com.example.projetodae.entities.User;
import com.example.projetodae.security.Hasher;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.hibernate.Hibernate;

import java.util.List;

@Stateless
public class UserBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Hasher hasher;

    public User findOrFail(String username){
        var user = entityManager.getReference(User.class, username);
        Hibernate.initialize(user);
        System.out.println(user +"  " + username);
        return user;
    }

    public boolean canLogin(String username, String password){
        var user = find(username);
        return user != null && user.getPassword().equals(hasher.hash(password));
    }

    public void create(String username, String password, TipoUser tipouser) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(hasher.hash(password)); // Consider hashing passwords before storing
        user.setTipouser(tipouser);
        entityManager.persist(user);
    }

    public User find(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        List<User> users = query.getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    public List<User> getAllUsers() {
        return entityManager.createNamedQuery("getAllUsers", User.class).getResultList();
    }

    public void updateUser(String username, String password, TipoUser tipouser) {
        User user = find(username);
        if (user != null) {
            user.setUsername(username);
            user.setPassword(password); // Consider hashing passwords
            user.setTipouser(tipouser);
            entityManager.merge(user);
        }
    }

    public void deleteUser(String username) {
        User user = find(username);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}

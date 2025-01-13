package com.example.projetodae.ejbs;

import com.example.projetodae.entities.TipoUser;
import com.example.projetodae.entities.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class UserBean {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(String username, String password, TipoUser tipouser) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Consider hashing passwords before storing
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

package com.example.projetodae.ejbs;


import com.example.projetodae.entities.User;
import com.example.projetodae.exceptions.MyEntityExistsException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.Hibernate;

@Stateless
public class UserBean {
    public enum TipoUser {
        Cliente,
        Logistica,
        Admin // Add more as needed
    }

    @PersistenceContext
    private EntityManager entityManager;

    public boolean exists(String username){

    Query query = entityManager.createQuery(
            "Select Count(u.username) From user u where u.username=:username",
            long.class
    );
        query.setParameter("username",username);
        return(Long)query.getSingleResult()>0L;
    }



    @Inject
    public User find(String username) {
        return entityManager.find(User.class, username);
    }

    public User findOrFail(String username) {
        var user =entityManager.getReference(User.class, username);
        Hibernate.initialize(user);
        return user;
    }

    public boolean canLogin(String username, String password) {
        var user=find(username);
        return user !=null && user.getPassword().equals(password);
    }

    public boolean setPassword(String username, String password) {
        User user=entityManager.find(User.class, username);
        user.setPassword(password);
        return true;
    }

    public void create(String username, String password, TipoUser tipoUser) {
        if(exists(username)){
            throw new MyEntityExistsException("User with username'" + username+ "'alreaedy Exists");
        }

        try {
            User user = new user(username,password,tipoUser)
        }catch(ConstraintViolationException e) {
            throw new MyconstraintViolationException(e);
        }
    }

    public List<User>getAll() {
        return entityManager.createNamedQUery("getAllUsers", User.class).getResultList();
    }

    public void updateUser(String username, String password)
    {
        User user =entityManager.find(User.class, username);

        if(user==null) {
            System.err.println("Error_User_NOT_FOUND: " + username);
            return
        }

        entityManager.lock(student,LockModeType.OPTIMISTIC)
        User.setUsername(username);
        if(password!=null) {
            User.setPassword(password);
        }
    }

    public void deleteUser(String username) {
        User user = entityManager.find(User.class, username);
        entityManager.remove(User);
    }





}

package com.cw.services.impl;

import com.cw.db.connectionFactory.ConnectionFactory;
import com.cw.db.dao.impl.JDBCUserDAO;
import com.cw.exceptions.UserNotFoundException;
import com.cw.entities.Artefact;
import com.cw.entities.Set;
import com.cw.entities.User;
import com.cw.services.UserServiceI;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Макс on 10.03.2018.
 */
@Service
public class UserService implements UserServiceI {

    private JDBCUserDAO jdbcUserDAO;
    private Connection connection;
    private ValidatorFactory factory;
    private SetService setService = new SetService();
    private ArtefactService artefactService = new ArtefactService();

    public UserService() {
        this.connection = ConnectionFactory.getConnection();
        this.jdbcUserDAO = new JDBCUserDAO(this.connection);
        factory = Validation.buildDefaultValidatorFactory();
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Set> getUserSets(User user) {
        List<Set> res = setService.getAllSetsByUserId(user.getId());
        res.forEach(s -> s.setUser(user));
        return res;
    }

    private List<Artefact> getUserArtefacts(User user) {
        return artefactService.getAllArtefactByUserId(user.getId());
    }

    @Override
    public User getUserById(int id) {
        User user = this.jdbcUserDAO.getUserById(id);
        if(user != null) {
            user.setSets(this.getUserSets(user));
            user.setUserArtefacts(this.getUserArtefacts(user));
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = this.jdbcUserDAO.getUserByUsername(username);
        if (user != null) {
            user.setSets(this.getUserSets(user));
            user.setUserArtefacts(this.getUserArtefacts(user));
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = this.jdbcUserDAO.getUserByEmail(email);
        if (user != null) {
            user.setSets(this.getUserSets(user));
            user.setUserArtefacts(this.getUserArtefacts(user));
        }
        return user;
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) throws UserNotFoundException{
        User user = this.jdbcUserDAO.getUserByEmailAndPassword(email, password);
        if (user != null) {
            user.setSets(this.getUserSets(user));
            user.setUserArtefacts(this.getUserArtefacts(user));
        }
        return user;
    }

    @Override
    public boolean addUser(User user) {
        Validator validator = factory.getValidator();
        java.util.Set<ConstraintViolation<User>> set = validator.validate( user );
        if (set.isEmpty())
            return this.jdbcUserDAO.addUser(user);
        else {
            // TODO refactor printing to logging
            System.err.println("[WARNING] REGISTRATION: Constrains failed for object: " + user);
            set.forEach(System.out::println);
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        return this.jdbcUserDAO.updateUser(user);
    }

    @Override
    public boolean deleteUserById(int id) {
        return this.jdbcUserDAO.deleteUserById(id);
    }

    @Override
    public boolean deleteUser(User user) {
        return this.deleteUserById(user.getId());
    }
}

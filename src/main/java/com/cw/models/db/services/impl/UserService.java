package com.cw.models.db.services.impl;

import com.cw.models.db.connectionFactory.ConnectionFactory;
import com.cw.models.db.dao.impl.JDBCUserDAO;
import com.cw.models.entities.Artefact;
import com.cw.models.entities.Set;
import com.cw.models.entities.User;
import com.cw.models.db.services.UserServiceI;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Макс on 10.03.2018.
 */
public class UserService implements UserServiceI {

    private JDBCUserDAO jdbcUserDAO;
    private Connection connection;

    public UserService() {
        this.connection = ConnectionFactory.getConnection();
        this.jdbcUserDAO = new JDBCUserDAO(this.connection);
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Set> getUserSets(User user) {
        SetService setService = new SetService();
        return setService.getAllSetsByUserId(user.getId());
    }

    private List<Artefact> getUserArtefacts(User user) {
        ArtefactService artefactService = new ArtefactService();
        return artefactService.getAllArtefactByUserId(user.getId());
    }

    @Override
    public User getUserById(int id) {
        User user = this.jdbcUserDAO.getUserById(id);
        user.setSets(this.getUserSets(user));
        user.setUserArtefacts(this.getUserArtefacts(user));
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = this.jdbcUserDAO.getUserByUsername(username);
        user.setSets(this.getUserSets(user));
        user.setUserArtefacts(this.getUserArtefacts(user));
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = this.jdbcUserDAO.getUserByEmail(email);
        user.setSets(this.getUserSets(user));
        user.setUserArtefacts(this.getUserArtefacts(user));
        return user;
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        User user = this.jdbcUserDAO.getUserByEmailAndPassword(email, password);
        user.setSets(this.getUserSets(user));
        user.setUserArtefacts(this.getUserArtefacts(user));
        return user;
    }

    @Override
    public boolean addUser(User user) {
        return this.jdbcUserDAO.addUser(user);
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
    public User deleteUser(User user) {
        return this.jdbcUserDAO.deleteUser(user);
    }
}

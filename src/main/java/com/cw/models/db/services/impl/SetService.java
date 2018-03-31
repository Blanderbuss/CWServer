package com.cw.models.db.services.impl;

import com.cw.models.db.connectionFactory.ConnectionFactory;
import com.cw.models.db.dao.impl.JDBCSetDAO;
import com.cw.models.entities.Artefact;
import com.cw.models.entities.Set;
import com.cw.models.entities.User;
import com.cw.models.db.services.SetServiceI;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Макс on 10.03.2018.
 */
@Service
public class SetService implements SetServiceI {
    private Connection connection = null;
    private JDBCSetDAO jdbcSetDAO = null;

    public SetService() {
        this.connection = ConnectionFactory.getConnection();
        jdbcSetDAO = new JDBCSetDAO(this.connection);
    }

    private List<Artefact> getAllSetArtefacts(Set set) {
        ArtefactService artefactService = new ArtefactService();
        return artefactService.getAllArtefactsBySetId(set.getId());
    }

    @Override
    public Set getSetById(int id) {
        Set set = this.jdbcSetDAO.getSetById(id);
        if (set != null)
            set.setArtefacts(this.getAllSetArtefacts(set));
        return set;
    }

    @Override
    public List<Set> getAllSetsByUserId(int id) {
        return this.jdbcSetDAO.getAllSetsByUserId(id);
    }

    @Override
    public User addSet(Set set, int userId) {
        UserService userService = new UserService();
        User user = userService.getUserById(userId);
        this.addSet(set, user);
        return user;
    }

    @Override
    public boolean addSet(Set set, User user) {
        return this.jdbcSetDAO.addSet(set, user);
    }

    @Override
    public boolean updateSet(Set set) {
        return this.jdbcSetDAO.updateSet(set);
    }

    @Override
    public boolean deleteSetById(int id) {
        return this.jdbcSetDAO.deleteSetById(id);
    }

    @Override
    public Set deleteSet(Set set) {
        this.deleteSetById(set.getId());
        return set;
    }

    @Override
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

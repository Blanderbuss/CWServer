package com.cw.models.db.services.impl;

import com.cw.models.db.connectionFactory.ConnectionFactory;
import com.cw.models.db.dao.impl.JDBCArtefactDAO;
import com.cw.models.entities.Artefact;
import com.cw.models.db.services.ArtefactServiceI;
import com.cw.models.entities.Set;
import com.cw.models.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ArtefactService implements ArtefactServiceI {
    private Connection connection = null;
    private JDBCArtefactDAO jdbcArtefactDAO = null;

    public ArtefactService() {
        this.connection = ConnectionFactory.getConnection();
        this.jdbcArtefactDAO = new JDBCArtefactDAO(this.connection);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Artefact getArtefactById(int id) {
        return this.jdbcArtefactDAO.getArtefactById(id);
    }

    @Override
    public List<Artefact> getAllArtefactsBySetId(int id) {
        return this.jdbcArtefactDAO.getAllArtefactsBySetId(id);
    }

    @Override
    public List<Artefact> getAllArtefactByUserId(int id) {
        return this.jdbcArtefactDAO.getAllArtefactByUserId(id);
    }

    @Override
    public boolean addArtefact(Artefact artefact) {
        return this.jdbcArtefactDAO.addArtefact(artefact);
    }

    @Override
    public boolean addArtefactToUserBackpack(Artefact artefact, User user) {
        return this.addArtefactToUserBackpack(artefact, user);
    }

    @Override
    public User addArtefactToUserBackpack(Artefact artefact, int user_id) {
        User user = new UserService().getUserById(user_id);
        this.jdbcArtefactDAO.addArtefactToUserBackpack(user, artefact);
        return user;
    }

    @Override
    public boolean addArtefactToSet(Set set, Artefact artefact) {
        return this.jdbcArtefactDAO.addArtefactToSet(set,  artefact);
    }

    @Override
    public Set addArtefactToSet(int set_id, Artefact artefact) {
        Set set = new SetService().getSetById(set_id);
        this.addArtefactToSet(set, artefact);
        return set;
    }

    @Override
    public boolean addArtefactsToSet(Set set, List<Integer> artefactsId) {
        return this.jdbcArtefactDAO.addArtefactsToSet(set, artefactsId);
    }

    @Override
    public Set addArtefactsToSet(int set_id, List<Integer> artefactsId) {
        Set set = new SetService().getSetById(set_id);
        this.addArtefactsToSet(set, artefactsId);
        return set;
    }

    @Override
    public boolean updateArtefact(Artefact artefact) {
        return this.jdbcArtefactDAO.updateArtefact(artefact);
    }

    @Override
    public boolean deleteArtefactById(int id) {
        return this.jdbcArtefactDAO.deleteArtefactById(id);
    }

    @Override
    public Artefact deleteArtefact(Artefact artefact) {
        this.deleteArtefactById(artefact.getId());
        return artefact;
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

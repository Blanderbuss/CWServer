package com.cw.models.db.services.impl;

import com.cw.models.db.connectionFactory.ConnectionFactory;
import com.cw.models.db.dao.impl.JDBCSetDAO;
import com.cw.models.entities.Artefact;
import com.cw.models.entities.Set;
import com.cw.models.entities.User;
import com.cw.models.db.services.SetServiceI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Макс on 10.03.2018.
 */
public class SetService implements SetServiceI {
    private Connection connection = null;
    private JDBCSetDAO jdbcSetDAO = null;

    private static final String GET_ALL_SETS_BY_USER_ID_SQL = "SELECT * FROM `sets` WHERE `user_id` = ?";

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
        set.setArtefacts(this.getAllSetArtefacts(set));
        return set;
    }

    @Override
    public List<Set> getAllSetsByUserId(int id) {
        Set set = null;
        List<Set> sets = new ArrayList<Set>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(SetService.GET_ALL_SETS_BY_USER_ID_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                set = new Set(resultSet.getString("name"), resultSet.getString("credits"));
                set.setId(resultSet.getInt("id"));

                sets.add(set);
            }

            resultSet.close();
            preparedStatement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sets;
    }

    @Override
    public boolean addSet(Set set, int userId) {
        return this.jdbcSetDAO.addSet(set, userId);
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
        return this.jdbcSetDAO.deleteSet(set);
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

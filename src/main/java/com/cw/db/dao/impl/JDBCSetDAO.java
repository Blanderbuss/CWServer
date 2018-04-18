package com.cw.db.dao.impl;

import com.cw.db.dao.SetDao;
import com.cw.entities.Set;
import com.cw.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Макс on 09.03.2018.
 */
public class JDBCSetDAO implements SetDao {
    private Connection connection = null;

    public JDBCSetDAO(Connection connection) {
        this.connection = connection;
    }

    private static final String GET_SET_BY_ID_SQL = "SELECT * FROM `sets` WHERE `id` = ?";

    private static final String GET_ALL_SETS_BY_USER_ID_SQL = "SELECT * FROM `sets` WHERE `user_id` = ?";

    private static final String ADD_SET_SQL = "INSERT INTO `sets` (`name`, `code`, `user_id`) VALUES (?, ?, ?)";

    private static final String UPDATE_SET_SQL = "UPDATE `sets` SET `name` = ?, `code` = ? WHERE `id` = ?";

    private static final String DELETE_SET_BY_ID_SQL = "DELETE FROM `sets` WHERE `id` = ?";

    @Override
    public Set getSetById(int id) {
//        this.connection = ConnectionFactory.getConnection();
        Set set = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCSetDAO.GET_SET_BY_ID_SQL);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isNotEmpty = resultSet.next();
            if (isNotEmpty) {
                set = new Set(resultSet.getString("name"), resultSet.getString("code"));
                set.setId(resultSet.getInt("id"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

    @Override
    public List<Set> getAllSetsByUserId(int id) {
        Set set = null;
        List<Set> sets = new ArrayList<Set>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCSetDAO.GET_ALL_SETS_BY_USER_ID_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                set = new Set(resultSet.getString("name"), resultSet.getString("code"));
                set.setId(resultSet.getInt("id"));

                sets.add(set);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sets;
    }

    @Override
    public boolean addSet(Set set, User user) {
//        this.connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCSetDAO.ADD_SET_SQL);
            preparedStatement.setString(1, set.getName());
            preparedStatement.setString(2, set.getCode());
            preparedStatement.setInt(3, user.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating set failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    set.setId(generatedKeys.getInt(1));
                    List<Set> newSets = user.getSets();
                    newSets.add(set);
                    user.setSets(newSets);
                }
                else {
                    throw new SQLException("Creating set failed, no ID obtained.");
                }
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateSet(Set set) {
        //        this.connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCSetDAO.UPDATE_SET_SQL);
            preparedStatement.setString(1, set.getName());
            preparedStatement.setString(2, set.getCode());
            preparedStatement.setInt(3, set.getId());

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteSetById(int id) {
//        this.connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCSetDAO.DELETE_SET_BY_ID_SQL);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

package com.cw.models.db.dao.impl;

import com.cw.models.db.dao.SetDao;
import com.cw.models.entities.Set;
import com.cw.models.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Макс on 09.03.2018.
 */
public class JDBCSetDAO implements SetDao {
    private Connection connection = null;

    public JDBCSetDAO(Connection connection) {
        this.connection = connection;
    }

    private static final String GET_SET_BY_ID_SQL = "SELECT * FROM `sets` WHERE `id` = ?";

    private static final String ADD_SET_SQL = "INSERT INTO `sets` (`name`, `code`, `user_id`) VALUES (?, ?, ?)";

    private static final String UPDATE_SET_SQL = "UPDATE `sets` SET `name` = ?, `code` = ?";

    private static final String DELETE_SET_BY_ID_SQL = "DELETE FROM `sets` WHERE `id` = ?";

    @Override
    public Set getSetById(int id) {
//        this.connection = ConnectionFactory.getConnection();
        Set set = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCSetDAO.GET_SET_BY_ID_SQL);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            set = new Set(resultSet.getString("name"), resultSet.getString("code"));
            set.setId(resultSet.getInt("id"));

            resultSet.close();
            preparedStatement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

    @Override
    public boolean addSet(Set set, int userId) {
//        this.connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCSetDAO.ADD_SET_SQL);
            preparedStatement.setString(1, set.getName());
            preparedStatement.setString(2, set.getCode());
            preparedStatement.setInt(3, userId);

            preparedStatement.execute();
            preparedStatement.close();
            this.connection.close();
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

            preparedStatement.execute();
            preparedStatement.close();
            this.connection.close();
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
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Set deleteSet(Set set) {
//        this.connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCSetDAO.DELETE_SET_BY_ID_SQL);
            preparedStatement.setInt(1, set.getId());

            preparedStatement.execute();
            preparedStatement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }
}

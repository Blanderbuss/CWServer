package com.cw.db.dao.impl;

import com.cw.exceptions.UserNotFoundException;
import com.cw.db.dao.UserDAO;
import com.cw.entities.User;

import java.sql.*;

/**
 * Created by Макс on 09.03.2018.
 */
public class JDBCUserDAO implements UserDAO {
    private Connection connection = null;

    public JDBCUserDAO(Connection connection) {
        this.connection = connection;
    }

    private static final String GET_USER_BY_ID_SQL = "SELECT * FROM `users` WHERE `users`.`id` = ?";
    private static final String GET_USER_BY_USERNAME_SQL = "SELECT * FROM `users` WHERE `users`.`username` = ?";
    private static final String GET_USER_BY_EMAIL_SQL = "SELECT * FROM `users` WHERE `users`.`email` = ?";
    private static final String GET_USER_BY_EMAIL_AND_PASSWORD_SQL = "SELECT * FROM `users` WHERE `users`.`email` = ? AND `users`.`password` = ?";

    private static final String ADD_USER_SQL = "INSERT INTO `users` (`username`, `email`, `password`, `experience`, `level`) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_USER_SQL = "UPDATE `users` SET `username` = ?, `email` = ?, `password` = ?, `experience` = ?, `level` = ? WHERE `id` = ?";

    private static final String DELETE_USER_BY_ID_SQL = "DELETE FROM `users` WHERE `id` = ?";

    @Override
    public User getUserById(int id) {
        User user = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCUserDAO.GET_USER_BY_ID_SQL);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isNotEmpty = resultSet.next();
            if (isNotEmpty) {
                user = new User(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getInt("experience"), resultSet.getInt("level"));
                user.setId(resultSet.getInt("id"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCUserDAO.GET_USER_BY_USERNAME_SQL);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isNotEmpty = resultSet.next();
            if (isNotEmpty) {
                user = new User(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getInt("experience"), resultSet.getInt("level"));
                user.setId(resultSet.getInt("id"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCUserDAO.GET_USER_BY_EMAIL_SQL);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isNotEmpty = resultSet.next();
            if (isNotEmpty) {
                user = new User(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getInt("experience"), resultSet.getInt("level"));
                user.setId(resultSet.getInt("id"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) throws UserNotFoundException {
        User user = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCUserDAO.GET_USER_BY_EMAIL_AND_PASSWORD_SQL);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isNotEmpty = resultSet.next();
            if (isNotEmpty) {
                user = new User(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getInt("experience"), resultSet.getInt("level"));
                user.setId(resultSet.getInt("id"));
            }
            else throw new UserNotFoundException("User with such email and password does not exists");

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean addUser(User user) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCUserDAO.ADD_USER_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPass());
            preparedStatement.setInt(4, user.getExperience());
            preparedStatement.setInt(5, user.getLvl());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
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
    public boolean updateUser(User user) {
        boolean success = false;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCUserDAO.UPDATE_USER_SQL);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPass());
            preparedStatement.setInt(4, user.getExperience());
            preparedStatement.setInt(5, user.getLvl());
            preparedStatement.setInt(6, user.getId());

            preparedStatement.execute();
            success = 0 < preparedStatement.getUpdateCount();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean deleteUserById(int id) {
        boolean success = false;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCUserDAO.DELETE_USER_BY_ID_SQL);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();
            success = 0 < preparedStatement.getUpdateCount();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
}

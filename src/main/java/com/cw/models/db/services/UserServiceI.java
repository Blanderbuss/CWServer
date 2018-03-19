package com.cw.models.db.services;

import com.cw.models.db.entities.User;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by denysmelnychenko on 2/11/18.
 */
@Service
public interface UserServiceI {
    User getUserById(int id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User getUserByEmailAndPassword(String email, String password);

    boolean addUser(User user);
    boolean updateUser(User user);

    boolean deleteUserById(int id);
    User deleteUser(User user);

    void closeConnection();
}

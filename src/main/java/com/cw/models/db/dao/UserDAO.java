package com.cw.models.db.dao;

import com.cw.exceptions.UserNotFoundException;
import com.cw.models.entities.User;

/**
 * Created by Макс on 09.03.2018.
 */
public interface UserDAO {
    User getUserById(int id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User getUserByEmailAndPassword(String email, String password) throws UserNotFoundException;

    boolean addUser(User user);
    boolean updateUser(User user);

    boolean deleteUserById(int id);
}

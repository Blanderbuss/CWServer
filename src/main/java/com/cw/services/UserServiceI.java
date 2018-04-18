package com.cw.services;

import com.cw.exceptions.UserNotFoundException;
import com.cw.entities.User;

/**
 * Created by denysmelnychenko on 2/11/18.
 */
public interface UserServiceI {
    User getUserById(int id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User getUserByEmailAndPassword(String email, String password) throws UserNotFoundException;

    boolean addUser(User user);
    boolean updateUser(User user);

    boolean deleteUserById(int id);
    // returns true if succeeds, false otherwise
    boolean deleteUser(User user);

    void closeConnection();

    //TODO this method. Add "throws AuthentificationException"
    void authentificate(User user);
    //TODO this method. Add "throws AuthentificationException"
    void register(User user);
}
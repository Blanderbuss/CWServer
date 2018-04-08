package com.cw.models.db.services.impl;

import com.cw.models.db.services.*;
import com.cw.models.entities.Set;
import com.cw.models.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* This class lists all operations available to client side.
* This is the class client side receives and operates with.
* Each method is commented in the interface file.
* */
@Service
public class SessionService implements SessionServiceI {

    private User user = null;
    @Autowired
    private ArtefactServiceI artService;
    @Autowired
    private BattleTypeServiceI battleTypeService;
    @Autowired
    private SetServiceI setService;
    @Autowired
    private UserServiceI userService;

    @Override
    public boolean login(String email, String pwd) {
        if (isLoggedIn())
            return false;
        user = userService.getUserByEmailAndPassword(email, pwd);
        return isLoggedIn();
    }

    @Override
    public void logout() {
        user = null;
    }

    @Override
    public boolean isLoggedIn() {
        return user != null;
    }

    @Override
    public User getUser() {
        if (isLoggedIn())
            return new User(user); // safe: user can do anything, yet he will brake nothing
        else
            return null;
    }

    @Override
    public boolean register(String username, String email, String pwd) {
        if (isLoggedIn())
            return false;
        return userService.addUser(new User(username, pwd, email));
    }

    @Override
    public void addNewSet(Set set) {

    }


    @Override
    public void chooseSetAsCurrent(Set set) {

    }


    @Override
    public void startFightAgainstBot() {

    }

    @Override
    public void startFightAgainstUser(User u) {

    }

    @Override
    public String getMyUserStatus() {
        return null;
    }

    @Override
    public List<User> getUsersReadyToFight() {
        return null;
    }

    @Override
    public String getFightStatistics() {
        return null;
    }

}

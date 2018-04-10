package com.cw.models.db.services.impl;

import com.cw.models.db.services.*;
import com.cw.models.entities.Artefact;
import com.cw.models.entities.Set;
import com.cw.models.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/*
* This class lists all operations available to client side.
* This is the class client side receives and operates with.
* Each method is commented in the interface file.
* */
@Service
public class SessionService implements SessionServiceI {

    private List<User> users = new LinkedList<User>();
    @Autowired
    private ArtefactServiceI artService;
    @Autowired
    private BattleTypeServiceI battleTypeService;
    @Autowired
    private SetServiceI setService;
    @Autowired
    private UserServiceI userService;

    //TODO Add exceptions
    @Override
    public User login(String email, String pwd) {
        System.out.println("User " + email + " tries to log in");
        User userFromDb = userService.getUserByEmailAndPassword(email, pwd);
        if(!isLoggedIn(userFromDb))
            users.add(userFromDb);
        return userFromDb;
    }

    @Override
    public void logout(User user) {
        if(isLoggedIn(user))
            users.remove(user);
    }

    @Override
    public boolean isLoggedIn(User user) {
        return users.contains(user);
    }

    @Override
    public boolean isLoggedIn(String userEmail) {
        for (User user:users) {
            if(user.getEmail().equals(userEmail))
                return true;
        }
        return false;
    }

    @Override
    public User getUser(User user) {
        if (isLoggedIn(user))
            return new User(user); // safe: client can do anything, yet he will brake nothing
        else
            return null;
    }

    @Override
    public boolean register(String username, String email, String pwd) {
        if (isLoggedIn(email))
            return false;
        return userService.addUser(new User(username, pwd, email));
    }

    @Override
    public void addNewSet(Set set, User user) {
        setService.addSet(set, user);
    }

    // artefact should be present in user backpack (user artifact list)
    // TODO link user.currentSet to database
    @Override
    public boolean addArtefactFromBackpackToCurrentSet(Artefact artefact, User user) {
        boolean artifactIsInUserBackpack = user.getUserArtefacts().contains(artefact);
        if (!artifactIsInUserBackpack)
            return false;
        boolean artefactIsAlreadyInSet = user.getCurrentSet().getArtefacts().contains(artefact);
        if (artefactIsAlreadyInSet)
            return false;
        boolean currentSetExistsInDBAmongUserSets =
                setService.getAllSetsByUserId(user.getId()).contains(user.getCurrentSet());
        if (!currentSetExistsInDBAmongUserSets)
            return false; // TODO change
        return artService.addArtefactToSet(user.getCurrentSet(), artefact);
    }

    @Override
    public void chooseSetAsCurrent(Set set, User user) {
        boolean presentInUserSets = user.getSets().contains(set);
        if (!presentInUserSets)
            addNewSet(set, user);
        // TODO here add some db query to set user.currentSet
    }

    @Override
    public void startFightAgainstBot(User user) {

    }

    @Override
    public void startFightAgainstUser(User u) {

    }

    @Override
    public String getMyUserStatus(User user) {
        return null;
    }

    @Override
    public List<User> getUsersReadyToFight() {
        return null;
    }

    @Override
    public String getFightStatistics(User user) {
        return null;
    }

}

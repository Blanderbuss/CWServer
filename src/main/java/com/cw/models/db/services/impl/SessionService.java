package com.cw.models.db.services.impl;

import com.cw.models.db.services.*;
import com.cw.models.entities.Artefact;
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
        System.out.println("User " + email + " tries to log in");
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
            return new User(user); // safe: client can do anything, yet he will brake nothing
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
        setService.addSet(set, user);
    }

    // artefact should be present in user backpack (user artifact list)
    // TODO link user.currentSet to database
    @Override
    public boolean addArtefactFromBackpackToCurrentSet(Artefact artefact) {
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
    public void chooseSetAsCurrent(Set set) {
        boolean presentInUserSets = user.getSets().contains(set);
        if (!presentInUserSets)
            addNewSet(set);
        // TODO here add some db query to set user.currentSet
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

package com.cw.models.db.services;

import com.cw.exceptions.UserNotFoundException;
import com.cw.models.entities.Artefact;
import com.cw.models.entities.Set;
import com.cw.models.entities.Tuple;
import com.cw.models.entities.User;

import java.util.List;

public interface SessionServiceI {

    // starts a valid session;
    // if user with the same credentials already has an unfinished session
    // then deactivate the user's session
    // returns a pair (val1, user) if login was successful, null otherwise
    Tuple<String, User> login(String email, String pwd) throws UserNotFoundException;

    // finishes session
    void logout(String accessToken);

    // indicates whether current session is active and valid
    boolean isLoggedIn(User user);
    @Deprecated
    boolean isLoggedInByEmail(String email); // TODO delete
    boolean isLoggedInByToken(String accessToken);
    boolean isUserRegistered(String email);

    // returns a user from which we can get his sets, artefacts and other fields
    User getUser(User user, String accessToken);

    // create a user
    // returns true if registration was successful, false otherwise
    boolean register(String usernme, String email, String pwd);

    // accessToken - token of current user's session
    void addNewSetToMyUser(Set set, String accessToken);

    // precondition: artefact should be present in user backpack (user artifact list) and currentSet is already in database
    boolean addArtefactFromBackpackToCurrentSet(Artefact artefact, String accessToken);

    // precondition: set should be present in user sets
    // accessToken - token of current user's session
    void chooseSetAsCurrent(Set set, String accessToken);

    //TODO add methods to modify and delete user sets

    // starts a fight
    // accessToken - token of current user's session
    void startFightAgainstBot(String accessToken);

    // user - the one we want to fight against
    // accessToken - token of current user's session
    void startFightAgainstUser(User user, String accessToken);

    String getMyUserStatus(String accessToken);

    // returns all users that are online whose status is ready-to-fight
    List<User> getUsersReadyToFight();

    String getMyUserFightStatistics(String accessToken);
}

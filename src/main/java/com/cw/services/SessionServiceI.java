package com.cw.services;

import com.cw.entities.Artefact;
import com.cw.entities.Set;
import com.cw.entities.Tuple;
import com.cw.entities.User;
import com.cw.exceptions.FighterException;
import com.cw.exceptions.UserNotFoundException;

import java.util.List;

public interface SessionServiceI {

    //test method
    String test() throws FighterException, InterruptedException;

    // starts a valid session;
    // if user with the same credentials already has an unfinished session
    // then deactivate the user's session
    // returns a pair (val1, user) if login was successful, null otherwise
    Tuple<String, User> login(String email, String pwd) throws UserNotFoundException;

    // finishes session
    void logout(String accessToken);

    // indicates whether current session is active and valid
    boolean isLoggedIn(User user);
    boolean isLoggedInByToken(String accessToken);
    boolean isUserRegistered(String email);

    // returns a user from which we can get his sets, artefacts and other fields
    User getUser(User user, String accessToken);

    // create a user
    // returns true if registration was successful, false otherwise
    boolean register(String username, String email, String pwd);

    // accessToken - token of current user's session
    void addNewSetToMyUser(Set set, String accessToken);

    boolean updateUserSet(Set set, String accessToken);

    // precondition: artefact should be present in user backpack (user artifact list) and currentSet is already in database
    boolean addArtefactFromBackpackToSet(Artefact artefact, Set set, String accessToken);

    // starts a fight
    // accessToken - token of current user's session
    int startFightAgainstBot(Set set, String accessToken, String stringBattleFieldType) throws FighterException;

    // user - the one we want to fight against
    // accessToken - token of current user's session
    // set - set chosen by current user to use in fight
    // returns id for getting result
    int startFightAgainstUsers(Set set, String accessToken, String stringBattleFieldType) throws FighterException;

    String getMyUserStatus(String accessToken);

    // deletes user represented by accessToken; used for client unit testing
    boolean deleteMyUser(String accessToken);

    // returns all users that are online whose status is ready-to-fight
    List<User> getUsersReadyToFight();

    //returns fight result
    String getFightResultForDuel(String accessToken, int resultId);

    String getMyUserFightStatistics(String accessToken);
}

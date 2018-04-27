package com.cw.services.impl;

import com.cw.entities.Artefact;
import com.cw.entities.Set;
import com.cw.entities.Tuple;
import com.cw.entities.User;
import com.cw.exceptions.IncorrectAccessTokenException;
import com.cw.exceptions.UserNotFoundException;
import com.cw.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

/*
* This class lists all operations available to client side.
* This is the class client side receives and operates with.
* Each method is commented in the interface file.
* */
@Service
public class SessionService implements SessionServiceI {

    // accessTokens -> users;
    // this map stores all users whose session is active and valid right now
    // accessTokens identifies session
    // only one accessToken is allowed per val2
    private Map<String, User> tokensToUsers = new HashMap<>();
    @Autowired
    private ArtefactServiceI artService;
    @Autowired
    private BattleTypeServiceI battleTypeService;
    @Autowired
    private SetServiceI setService;
    @Autowired
    private UserServiceI userService;
    @Autowired
    private FightServiceI fightService;

    @Override
    public Tuple<String, User> login(String email, String pwd) throws UserNotFoundException, IncorrectAccessTokenException {
        User userFromDb = userService.getUserByEmailAndPassword(email, pwd);
        // deactivate active session of current user, who had logged in previously
        if (isLoggedIn(userFromDb)) {
            String userToken = tokensToUsers.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().equals(userFromDb))
                    .findFirst()
                    .get()
                    .getKey();
            logout(userToken);
        }
        String token = "";
        // loop until unique token is found; ensures absence of hash collisions
        do {
            token = TokenGenerator.generateToken(userFromDb.getEmail());
            System.err.println("[INFO] TOKENGEN:Generated token: " + token);
        } while (tokensToUsers.containsKey(token));
        final String newToken = token;
        System.err.println("[INFO] TOKENGEN:Activated token: " + newToken);
        tokensToUsers.put(newToken, userFromDb);
        return new Tuple<>(token, userFromDb);
    }

    @Override
    public void logout(String accessToken) throws IncorrectAccessTokenException {
        if (!isLoggedInByToken(accessToken))
            throw new IncorrectAccessTokenException("NO USER WITH SUCH TOKEN AUTHED");
        System.err.println("[INFO] TOKENGEN:Deactivated token: " + accessToken);
        tokensToUsers.remove(accessToken);
    }

    @Override
    public boolean isLoggedIn(User user) {
        return tokensToUsers.containsValue(user);
    }

    @Override
    public boolean isLoggedInByToken(String accessToken) {
        return tokensToUsers.containsKey(accessToken);
    }

    @Override
    public boolean isUserRegistered(String email) {
        return userService.getUserByEmail(email) != null;
    }

    @Override
    public User getUser(User user, String accessToken) throws IncorrectAccessTokenException {
        if (isLoggedInByToken(accessToken))
            return new User(userService.getUserByEmail(user.getEmail())); // safe: client can do anything, yet he will brake nothing
        else
            throw new IncorrectAccessTokenException("NO USER WITH SUCH TOKEN AUTHED");
    }

    @Override
    public boolean register(String username, String email, String pwd) throws IncorrectAccessTokenException {
        if (isUserRegistered(email))
            throw new IncorrectAccessTokenException("NO USER WITH SUCH TOKEN AUTHED");
        return userService.addUser(new User(username, pwd, email));
    }

    @Override
    public boolean updateUserSet(Set set, String accessToken) throws IncorrectAccessTokenException {
        if (!isLoggedInByToken(accessToken))
            throw new IncorrectAccessTokenException("NO USER WITH SUCH TOKEN AUTHED");
        boolean artefactsAdded = artService.updateSetArtifacts(set);
        return artefactsAdded && setService.updateSet(set);
    }

    @Override
    public void addNewSetToMyUser(Set set, String accessToken) throws IncorrectAccessTokenException {
        if (!isLoggedInByToken(accessToken))
            throw new IncorrectAccessTokenException("NO USER WITH SUCH TOKEN AUTHED");
        User user = tokensToUsers.get(accessToken);
        setService.addSet(set, user);
    }

    @Override
    public boolean addArtefactFromBackpackToSet(Artefact artefact, Set set, String accessToken) throws IncorrectAccessTokenException {
        if (!isLoggedInByToken(accessToken))
            throw new IncorrectAccessTokenException("NO USER WITH SUCH TOKEN AUTHED");
        User user = tokensToUsers.get(accessToken);
        boolean artifactIsInUserBackpack = user.getUserArtefacts().contains(artefact);
        if (!artifactIsInUserBackpack)
            return false;
        boolean artefactIsAlreadyInSet = set.getArtefacts().contains(artefact);
        if (artefactIsAlreadyInSet)
            return false;
        boolean chosenSetExistsInDBAmongUserSets =
                setService.getAllSetsByUserId(user.getId()).contains(set);
        if (!chosenSetExistsInDBAmongUserSets)
            return false;
        return artService.addArtefactToSet(set, artefact);
    }

    @Override
    public int startFightAgainstBot(Set set, String accessToken, String stringBattleFieldType) throws IncorrectAccessTokenException {
        if (!isLoggedInByToken(accessToken))
            throw new IncorrectAccessTokenException("NO USER WITH SUCH TOKEN AUTHED");
        Set vikingSet = setService.getSetById(10);
        vikingSet.setUser(userService.getUserById(3));
        fightService.readyForFight(vikingSet, stringBattleFieldType);
        return fightService.readyForFight(set, stringBattleFieldType);
    }

    @Override
    public int startFightAgainstUsers(Set set, String accessToken, String stringBattleFieldType) throws IncorrectAccessTokenException {
        if (!isLoggedInByToken(accessToken)) {
            throw new IncorrectAccessTokenException("NO USER WITH SUCH TOKEN AUTHED");
        }
        return fightService.readyForFight(set, stringBattleFieldType);
    }

    @Override
    public String getMyUserStatus(String accessToken) throws IncorrectAccessTokenException {
        if (!isLoggedInByToken(accessToken))
            throw new IncorrectAccessTokenException("NO USER WITH SUCH TOKEN AUTHED");
        User user = tokensToUsers.get(accessToken);
        //return user.getStatus();
        return null;
    }

    @Override
    public boolean deleteMyUser(String accessToken) {
        if (!isLoggedInByToken(accessToken))
            return false;
        User u = tokensToUsers.remove(accessToken);
        return userService.deleteUser(u);
    }

    @Override
    public List<User> getUsersReadyToFight() {
        return new ArrayList<>(tokensToUsers.values());
    }

    @Override
    public String getFightResultForType(String accessToken, int resultId, String typeOfBattleField) throws IncorrectAccessTokenException {
        if (!isLoggedInByToken(accessToken))
            throw new IncorrectAccessTokenException("NO USER WITH SUCH TOKEN AUTHED");
        return fightService.getResult(resultId, typeOfBattleField);
    }

    @Override
    public String getMyUserFightStatistics(String accessToken) throws IncorrectAccessTokenException {
        if (!isLoggedInByToken(accessToken))
            throw new IncorrectAccessTokenException("NO USER WITH SUCH TOKEN AUTHED");
        //return user.getStatistics();
        return null;
    }

    // author: https://github.com/davidadale
    private static class TokenGenerator {

        static SecureRandom random = new SecureRandom();

        synchronized static String generateToken(String username) {
            long longToken = Math.abs(random.nextLong());
            String random = Long.toString(longToken, 16);
            return (username + ":" + random);
        }
    }
}

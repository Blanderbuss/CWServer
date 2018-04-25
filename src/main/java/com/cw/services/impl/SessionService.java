package com.cw.services.impl;

import com.cw.entities.Artefact;
import com.cw.entities.Set;
import com.cw.entities.Tuple;
import com.cw.entities.User;
import com.cw.exceptions.FighterException;
import com.cw.exceptions.UserNotFoundException;
import com.cw.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public String test() throws FighterException, InterruptedException {
        Set set1 = setService.getSetById(6);
        ArrayList<Artefact> newArts = new ArrayList<Artefact>();
        newArts.add(artService.getArtefactById(1));
        set1.setArtefacts(newArts);
        System.out.println(artService.updateSetArtifacts(set1));
        System.out.println(setService.updateSet(set1));
        /*Set set1 = setService.getSetById(6);
        System.out.println(set1);
        Set set2 = setService.getSetById(6);
        System.out.println(set2);
        int accessId1 = fightService.readyForFight(set1, "Duel");
        int accessId2 = fightService.readyForFight(set2, "Duel");
        wait(1000);
        String res = fightService.getResult(accessId1,"Duel");
        System.out.println(res);*/
        return "";
    }

    @Override
    public Tuple<String, User> login(String email, String pwd) throws UserNotFoundException {
        System.out.println("User " + email + " tries to log in");
        User userFromDb = userService.getUserByEmailAndPassword(email, pwd);
        // deactivate active session of current user, who had logged in previously
        // TODO write jUnit auto tests to verify it
        if (isLoggedIn(userFromDb)) {
            String userToken = tokensToUsers.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().equals(userFromDb))
                    .findFirst()
                    .get()
                    .getKey();
            logout(userToken);
            System.out.println("Deactivated token: " + userToken);
        }
        String token = "";
        // loop until unique token is found; ensures absence of hash collisions
        do {
            token = TokenGenerator.generateToken(userFromDb.getEmail());
            System.out.println("Generated token: " + token);
        } while (tokensToUsers.containsKey(token));
        final String newToken = token;
        System.out.println("Activated token: " + newToken);
        tokensToUsers.put(newToken, userFromDb);
        return new Tuple<>(token, userFromDb);
    }

    @Override
    public void logout(String accessToken) {
        tokensToUsers.remove(accessToken);
    }

    @Override
    public boolean isLoggedIn(User user) {
        return tokensToUsers.containsValue(user);
    }

    // TODO delete
    @Override
    @Deprecated
    public boolean isLoggedInByEmail(String userEmail) {
        return tokensToUsers.values().stream().anyMatch(u -> u.getEmail().equals(userEmail));
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
    public User getUser(User user, String accessToken) {
        if (isLoggedInByToken(accessToken))
            return new User(userService.getUserByEmail(user.getEmail())); // safe: client can do anything, yet he will brake nothing
        else
            return null;
    }

    @Override
    public boolean register(String username, String email, String pwd) {
        if (isUserRegistered(email))
            return false;
        return userService.addUser(new User(username, pwd, email));
    }

    @Override
    public boolean updateUserSet(Set set, String accessToken) {
        if (!isLoggedInByToken(accessToken))
            return false;
        boolean artefactsAdded = artService.updateSetArtifacts(set);
        return artefactsAdded && setService.updateSet(set);
    }

    @Override
    public void addNewSetToMyUser(Set set, String accessToken) {
        if (!isLoggedInByToken(accessToken))
            return;
        User user = tokensToUsers.get(accessToken);
        setService.addSet(set, user);
    }

    @Override
    public boolean addArtefactFromBackpackToSet(Artefact artefact, Set set, String accessToken) {
        if (!isLoggedInByToken(accessToken))
            return false;
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
    public void startFightAgainstBot(Set set, String accessToken, String stringBattleFieldType) {
        if (!isLoggedInByToken(accessToken))
            return;
        User userWhoStartsFight = tokensToUsers.get(accessToken);
        // TODO start fight
    }

    @Override
    public int startFightAgainstUsers(Set set, String accessToken, String stringBattleFieldType) throws FighterException {
        if (!isLoggedInByToken(accessToken))
            //TODO throw exception
            return -1;
        return fightService.readyForFight(set, stringBattleFieldType);
    }

    @Override
    public String getMyUserStatus(String accessToken) {
        if (!isLoggedInByToken(accessToken))
            return null;
        User user = tokensToUsers.get(accessToken);
        //return user.getStatus(); // TODO add status linking
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
    public String getFightResultForDuel(String accessToken, int resultId){
        if (!isLoggedInByToken(accessToken))
            //TODO throw exception
            return "";
        return fightService.getResult(resultId, "Duel");
    }

    @Override
    public String getMyUserFightStatistics(String accessToken) {
        if (!isLoggedInByToken(accessToken))
            return null;
        //return user.getStatistics(); // TODO add stats linking
        return null;
    }

    // author: https://github.com/davidadale
    private static class TokenGenerator {

        protected static SecureRandom random = new SecureRandom();

        public synchronized static String generateToken(String username) {
            long longToken = Math.abs(random.nextLong());
            String random = Long.toString(longToken, 16);
            return (username + ":" + random);
        }
    }
}

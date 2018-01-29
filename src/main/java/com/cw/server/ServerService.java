package com.cw.server;

import com.cw.appif.ServerServiceIF;
import com.cw.exceptions.FighterException;
import com.cw.exceptions.UserException;
import com.cw.models.FighterA;
import com.cw.models.User;

import java.util.ArrayList;

public class ServerService implements ServerServiceIF {

    static ArrayList<User> clients = new ArrayList<>();
    static ArrayList<FighterA> fighters = new ArrayList<>();
    private final int type = 2;

    @Override
    public boolean register(User user) throws UserException {
        //TODO Verify data and add to db
        clients.add(user);
        System.out.println("User " + user + " registered");
        return true;
    }

    @Override
    public boolean auth(User user) throws UserException {
        //TODO Check database for user
        clients.add(user);
        System.out.println("User " + user + " authed");
        return true;
    }

    @Override
    public boolean regFighter(FighterA fighter) throws FighterException {
        //TODO Check database for fighter
        fighter.setStatus(FighterA.Status.REGISTERED);
        fighters.add(fighter);
        if(fighters.size()==type)new Thread(new BattleField(fighters)).start();
        return true;
    }
}

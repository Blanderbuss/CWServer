package com.cw.server;

import com.cw.appif.ServerServiceIF;
import com.cw.exceptions.UserException;
import com.cw.exceptions.FighterException;
import com.cw.models.FighterA;
import com.cw.models.User;

import java.util.ArrayList;

public class ServerService implements ServerServiceIF{

    static ArrayList<User> clients = new ArrayList<>();
    static ArrayList<FighterA> fighters = new ArrayList<>();

    @Override
    public boolean register(User user) throws UserException {
        //TODO Verify data and add to db
        clients.add(user);
        return true;
    }

    @Override
    public boolean auth(User user) throws UserException {
        //TODO Check database for user
        clients.add(user);
        System.out.println("hi");
        return true;
    }

    @Override
    public boolean regFighter(FighterA fighter) throws FighterException {
        //TODO Check database for fighter
        fighter.setStatus(FighterA.Status.REGISTERED);
        fighters.add(fighter);
        return true;
    }

    private void startFight(){

    }
}

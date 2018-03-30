package com.cw.server;

import com.cw.appif.ServerServiceIF;
import com.cw.exceptions.FighterException;
import com.cw.exceptions.UserException;
import com.cw.models.Fighter;
import com.cw.models.entities.Artefact;
import com.cw.models.entities.Set;
import com.cw.models.entities.User;
import com.cw.models.db.services.ArtefactServiceI;
import com.cw.models.db.services.BattleTypeServiceI;
import com.cw.models.db.services.SetServiceI;
import com.cw.models.db.services.UserServiceI;
import com.cw.server.factory.ActionExecutor;
import com.cw.server.factory.FighterFactory;

import java.util.ArrayList;
import java.util.List;

public class ServerService implements ServerServiceIF {

    private ArtefactServiceI artefactService;

    private BattleTypeServiceI battleTypeServiceI;

    private SetServiceI setService;

    private UserServiceI userService;

    static ArrayList<User> clients = new ArrayList<>();
    static ArrayList<Fighter> fighters = new ArrayList<>();
    //TODO do BattlerType from entities
    private final int type = 2;

    @Override
    public boolean register(User user) throws UserException {
        //TODO Verify data and add to db
        clients.add(user);
        System.out.println("User " + user.getEmail() + " registered");
        return true;
    }

    @Override
    public boolean auth(User user) throws UserException {
        //TODO Check database for user
        clients.add(user);
        System.out.println("User " + user.getEmail() + " authed");
        return true;
    }

    @Override
    public boolean addSet(String name, int lvl, List<Artefact> artefacts) throws FighterException {
        return false;
    }

    @Override
    public boolean readyForFight(int id) throws FighterException {
        //TODO Check database for fighter
        Set set = setService.getSetById(id);
        ActionExecutor actionExecutor = FighterFactory.getActionDoer(String.valueOf(id),set.getCode());
        Fighter fighter = new Fighter(set, actionExecutor);
        fighter.setStatus(Fighter.Status.REGISTERED);
        fighters.add(fighter);
        System.out.println("Fighter " + fighter.getName() + " registered");
        if(fighters.size()==type)new Thread(new BattleField(fighters)).start();
        return true;
    }
}

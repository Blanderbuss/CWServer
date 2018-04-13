package com.cw.BattleLogic.ActionImpl;

import com.cw.BattleLogic.ActionAbstract;
import com.cw.BattleLogic.Fighter;
import com.cw.models.db.services.impl.FightService;

public class ActionDefend extends ActionAbstract{

    public ActionDefend(Fighter actor, Fighter target) {
        super(actor, target);
    }

    @Override
    protected Fighter.Stance getNewStanceForActor(){
        return Fighter.Stance.DEFENDING;
    }

    @Override
    protected Fighter.Stance getNewStanceForTarget(){
        return Fighter.Stance.DEFENDING;
    }

    @Override
    protected String report() {
        return "Fighter " + this.getActor() + " is in defending position now";
    }
}

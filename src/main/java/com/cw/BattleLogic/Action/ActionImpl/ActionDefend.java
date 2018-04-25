package com.cw.BattleLogic.Action.ActionImpl;

import com.cw.BattleLogic.Action.ActionAbstract;
import com.cw.BattleLogic.Fighter;

import static com.cw.BattleLogic.Action.ActionConstants.DEFEND_ARMOR_BOOST;
import static com.cw.BattleLogic.Action.ActionConstants.DEFEND_EVASION_BOOST;
import static com.cw.BattleLogic.Action.ActionConstants.DEFEND_STAMINA_REGEN_BOOST;

public class ActionDefend extends ActionAbstract{

    private String res;

    public ActionDefend(Fighter actor, Fighter target) {
        super(actor, target);
        if(actor!=target){
            this.res = "Fighter " + this.getActor()
                    + " tried to defend somebody else, but he can defend only himself";
            this.setPerformFailed(true);
        }
        this.res = "Fighter " + this.getActor() + " is in defending position now";
    }

    @Override
    protected int getStaminaRegenChangeForTarget(){
        return DEFEND_STAMINA_REGEN_BOOST;
    }

    @Override
    protected int getEvasionChangeForTarget(){
        return DEFEND_EVASION_BOOST;
    }

    @Override
    protected int getArmorChangeForTarget(){
        return DEFEND_ARMOR_BOOST;
    }

    //Note: target is changed last, so only changes to him is important if actor and target re same
    @Override
    protected Fighter.Stance getNewStanceForTarget(){
        return Fighter.Stance.DEFENDING;
    }

    @Override
    protected String report() {
        return res;
    }
}

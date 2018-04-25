package com.cw.BattleLogic.Action.ActionImpl;

import com.cw.BattleLogic.Action.ActionAbstract;
import com.cw.BattleLogic.Fighter;

import static com.cw.BattleLogic.Action.ActionConstants.DEFEND_ARMOR_BOOST;
import static com.cw.BattleLogic.Action.ActionConstants.DEFEND_EVASION_BOOST;
import static com.cw.BattleLogic.Action.ActionConstants.DEFEND_STAMINA_REGEN_BOOST;

public class ActionFree extends ActionAbstract{

    private String res;

    public ActionFree(Fighter actor, Fighter target){
        super(actor, target);
        if(actor!=target){
            this.res = "Fighter " + this.getActor()
                    + " tried to put in free position somebody else, but he can free only himself";
            this.setPerformFailed(true);
        }
        this.res = "Fighter " + this.getActor() + " is in free position now";
    }

    @Override
    protected int getStaminaRegenChangeForTarget(){
        if(getTarget().getStance()== Fighter.Stance.DEFENDING)
            return -DEFEND_STAMINA_REGEN_BOOST;
        else return 0;
    }

    @Override
    protected int getEvasionChangeForTarget(){
        if(getTarget().getStance()== Fighter.Stance.DEFENDING)
            return -DEFEND_EVASION_BOOST;
        else return 0;
    }

    @Override
    protected int getArmorChangeForTarget(){
        if(getTarget().getStance()== Fighter.Stance.DEFENDING)
            return -DEFEND_ARMOR_BOOST;
        else return 0;
    }

    @Override
    protected String report() {
        return res;
    }
}

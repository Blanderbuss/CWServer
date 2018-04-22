package com.cw.BattleLogic.Action.ActionImpl;

import com.cw.BattleLogic.Action.ActionAbstract;
import com.cw.BattleLogic.Fighter;

import java.util.Random;

import static com.cw.BattleLogic.Action.ActionConstants.FIREBALL_MANA_COST;
import static com.cw.BattleLogic.Action.ActionConstants.FIREBALL_DAMAGE;

public class ActionFireball extends ActionAbstract{

    private static Random rand = new Random();

    private StringBuilder result;

    public ActionFireball(Fighter actor, Fighter target) {
        super(actor, target);
        if(this.getActor().getStance()==Fighter.Stance.DEFENDING) {
            result = new StringBuilder("Fighter " + this.getActor().getName() + " tried to throw fireball, but he is in defending position, so he can`t\n");
            //TODO throw exception
            return;
        }
        result = new StringBuilder("Fighter " + this.getActor().getName() + " tries to throw fireball\n");

    }

    protected int getStaminaChangeForActor(){
        return FIREBALL_MANA_COST;
    }

    @Override
    protected int getHpChangeForTarget(){
        //Checking if target evaded the attack
        int hitChance = 100 - getTarget().getEvasion();
        int randomInt = rand.nextInt(100) + 1;
        if(randomInt >= hitChance){
            //TODO find out why is it yellow.
            result.append("Target "+ getTarget().toString() + " evaded the fireball\n");
            return 0;
        }
        else {
            //Damage formula
            int damageToBeDone = FIREBALL_DAMAGE;
            result.append("Target " + getTarget().toString() + " received " + damageToBeDone + " damage from fireball\n");
            return damageToBeDone;
        }
    }

    @Override
    protected String report() {
        System.out.println(result.toString());
        return result.toString();
    }
}

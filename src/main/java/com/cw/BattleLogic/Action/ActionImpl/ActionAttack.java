package com.cw.BattleLogic.Action.ActionImpl;

import com.cw.BattleLogic.Action.ActionAbstract;
import com.cw.BattleLogic.Fighter;

import java.util.Random;

public class ActionAttack extends ActionAbstract {

    private static Random rand = new Random();

    private StringBuilder result;

    public ActionAttack(Fighter actor, Fighter target) {
        super(actor, target);
        //TODO generify this with new Stance class
        if(this.getActor().getStance()==Fighter.Stance.DEFENDING)
            //TODO throw exception
            return;
        result = new StringBuilder("Fighter " + this.getActor().getName() + " tries to attack\n");
    }

    @Override
    protected int getHpChangeForTarget(){
        //Checking if target evaded the attack
        int hitChance = 100 - getTarget().getEvasion();
        int randomInt = rand.nextInt(100) + 1;
        if(randomInt >= hitChance){
            //TODO find out why is it yellow.
            result.append("Target "+ getTarget().toString() + " evaded the attack\n");
            return 0;
        }
        else {
            //Damage formula
            double pureDamage = this.getActor().getLvl() * 2 + 3;
            //Armor formula
            double damageReduction = this.getTarget().getArmor() * 0.05 / (1 + this.getTarget().getArmor() * 0.05);
            int damageToBeDone = (int) Math.floor(pureDamage * damageReduction);
            result.append("Target " + getTarget().toString() + " received " + damageToBeDone + " damage\n");
            return damageToBeDone;
        }
    }

    @Override
    protected Fighter.Stance getNewStanceForTarget(){
        if(this.getTarget().getStance()== Fighter.Stance.DEFENDING){
            result.append("Target "+ getTarget().toString() + " is no longer in defending position\n");
            return Fighter.Stance.FREE;
        }
        else
            return this.getTarget().getStance();
    }

    @Override
    protected String report() {
        return result.toString();
    }
}

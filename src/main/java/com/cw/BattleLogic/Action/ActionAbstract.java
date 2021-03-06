package com.cw.BattleLogic.Action;

import com.cw.BattleLogic.Fighter;

public abstract class ActionAbstract {

    private Fighter actor;
    private Fighter target;

    private boolean performFailed;

    public ActionAbstract(Fighter actor, Fighter target) {
        this.actor = actor;
        this.target = target;
    }

    public Fighter getActor() {
        return actor;
    }

    public void setActor(Fighter actor) {
        this.actor = actor;
    }

    public Fighter getTarget() {
        return target;
    }

    public void setTarget(Fighter target) {
        this.target = target;
    }

    public boolean isPerformFailed() {
        return performFailed;
    }

    public void setPerformFailed(boolean performFailed) {
        this.performFailed = performFailed;
    }

    //Method that does all the calculation
    public String perform(){
        if(performFailed)
            return this.report();

        int possibleNewActorHp = actor.getCurHp()+this.getHpChangeForActor();
        int newActorHp = Math.min(possibleNewActorHp,actor.getMaxHp());

        int possibleNewActorStamina = actor.getCurStamina()+this.getStaminaChangeForActor();
        int newActorStamina = Math.min(possibleNewActorStamina,actor.getMaxStamina());

        int possibleNewActorMana = actor.getCurMana()+this.getManaChangeForActor();
        int newActorMana = Math.min(possibleNewActorMana,actor.getMaxMana());
        
        int newActorHpRegen = actor.getRegenHp()+this.getHpRegenChangeForActor();
        int newActorStaminaRegen = actor.getRegenStamina()+this.getStaminaRegenChangeForActor();
        int newActorManaRegen = actor.getRegenMana()+this.getManaRegenChangeForActor();

        int newActorAttack = actor.getAttack()+this.getAttackChangeForActor();
        int newActorEvasion = actor.getEvasion()+this.getEvasionChangeForActor();
        int newActorArmor = actor.getArmor()+this.getArmorChangeForActor();

        //Speed is only change for the next action
        int newActorSpeed = actor.getMaxSpeed()*this.getSpeedChangeForActor();

        Fighter.Stance newActorStance = this.getNewStanceForActor();

        actor.setCurHp(newActorHp);
        actor.setCurStamina(newActorStamina);
        actor.setCurMana(newActorMana);
        actor.setRegenHp(newActorHpRegen);
        actor.setRegenStamina(newActorStaminaRegen);
        actor.setRegenMana(newActorManaRegen);
        actor.setAttack(newActorAttack);
        actor.setEvasion(newActorEvasion);
        actor.setArmor(newActorArmor);
        actor.setCurSpeed(newActorSpeed);
        actor.setStance(newActorStance);

        int possibleNewTargetHp = target.getCurHp()+this.getHpChangeForTarget();
        int newTargetHp = Math.min(possibleNewTargetHp,target.getMaxHp());

        int possibleNewTargetStamina = target.getCurStamina()+this.getStaminaChangeForTarget();
        int newTargetStamina = Math.min(possibleNewTargetStamina,target.getMaxStamina());

        int possibleNewTargetMana = target.getCurMana()+this.getManaChangeForTarget();
        int newTargetMana = Math.min(possibleNewTargetMana,target.getMaxMana());

        int newTargetHpRegen = target.getRegenHp()+this.getHpRegenChangeForTarget();
        int newTargetStaminaRegen = target.getRegenStamina()+this.getStaminaRegenChangeForTarget();
        int newTargetManaRegen = target.getRegenMana()+this.getManaRegenChangeForTarget();

        int newTargetAttack = target.getAttack()+this.getAttackChangeForTarget();
        int newTargetEvasion = target.getEvasion()+this.getEvasionChangeForTarget();
        int newTargetArmor = target.getArmor()+this.getArmorChangeForTarget();

        //Speed is only change for the next action
        int newTargetSpeed = target.getMaxSpeed()*this.getSpeedChangeForTarget();

        Fighter.Stance newTargetStance = this.getNewStanceForTarget();

        target.setCurHp(newTargetHp);
        target.setCurStamina(newTargetStamina);
        target.setCurMana(newTargetMana);
        target.setRegenHp(newTargetHpRegen);
        target.setRegenStamina(newTargetStaminaRegen);
        target.setRegenMana(newTargetManaRegen);
        target.setAttack(newTargetAttack);
        target.setEvasion(newTargetEvasion);
        target.setArmor(newTargetArmor);
        target.setCurSpeed(newTargetSpeed);
        target.setStance(newTargetStance);

        return this.report();
    }
    
    //Must be overwritten in subclasses
    //Returns result of action in String format to be given to user
    protected abstract String report();

    //All following methods must be overwritten in subclasses if needed

    protected int getHpChangeForActor(){
        return 0;
    }

    protected int getStaminaChangeForActor(){
        return 0;
    }

    protected int getManaChangeForActor(){
        return 0;
    }
    
    protected int getHpRegenChangeForActor(){
        return 0;
    }
    
    protected int getStaminaRegenChangeForActor(){
        return 0;
    }

    protected int getManaRegenChangeForActor(){
        return 0;
    }

    protected int getAttackChangeForActor(){
        return 0;
    }

    protected int getEvasionChangeForActor(){
        return 0;
    }

    protected int getArmorChangeForActor(){
        return 0;
    }


    //In percents to maximum speed
    protected int getSpeedChangeForActor(){
        return 1;
    }

    //Default new stance - the old one
    protected Fighter.Stance getNewStanceForActor(){
        return actor.getStance();
    }

    protected int getHpChangeForTarget(){
        return 0;
    }

    protected int getStaminaChangeForTarget(){
        return 0;
    }

    protected int getManaChangeForTarget(){
        return 0;
    }

    protected int getHpRegenChangeForTarget(){
        return 0;
    }

    protected int getStaminaRegenChangeForTarget(){
        return 0;
    }

    protected int getManaRegenChangeForTarget(){
        return 0;
    }

    protected int getAttackChangeForTarget(){
        return 0;
    }

    protected int getEvasionChangeForTarget(){
        return 0;
    }

    protected int getArmorChangeForTarget(){
        return 0;
    }

    //In percents to maximum speed
    protected int getSpeedChangeForTarget(){
        return 1;
    }

    //Default new stance - the old one
    protected Fighter.Stance getNewStanceForTarget(){
        return actor.getStance();
    }
}

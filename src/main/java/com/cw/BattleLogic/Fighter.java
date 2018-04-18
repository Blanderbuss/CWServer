package com.cw.BattleLogic;



import com.cw.entities.Artefact;
import com.cw.entities.Set;
import com.cw.factory.ActionExecutor;
import com.cw.factory.FighterFactory;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

public class Fighter implements Serializable{
    //TODO More properties
    public class ActTarget{
        @NotNull
        private Action action;
        @Min(0)
        private int target;

        public ActTarget(Action action, int target) {
            this.action = action;
            this.target = target;
        }

        public Action getAction() {
            return action;
        }

        public void setAction(Action action) {
            this.action = action;
        }

        public int getTarget() {
            return target;
        }

        public void setTarget(int target) {
            this.target = target;
        }

    }

    public enum Action {
        ATTACK,
        DEFEND
    }

    public enum Stance {
        FREE,
        DEFENDING
    }

    @Size(min=3, max=80)
    @NotNull
    private String name;
    @Min(0)
    @Max(80)
    private int lvl;

    @NotNull
    private Stance stance;
    @Min(0)
    private int maxHp;
    @Min(0)
    private int maxMana;
    @Min(0)
    private int maxStamina;
    private int regenHp;
    private int regenMana;
    private int regenStamina;
    @Min(0)
    @Max(100)
    private int evasion;
    @Min(0)
    private int armor;
    @Min(0)
    private int maxSpeed;
    @Min(0)
    private int curHp;
    @Min(0)
    private int curMana;
    @Min(0)
    private int curStamina;
    @Min(0)
    private int curSpeed;

    private ActionExecutor actionExecutor;

    private List<Artefact> artefacts;

    public Fighter() {
    }

    public Fighter(Set set){
        this.name=set.getName();
        this.lvl=/*set.getLvl()*/1;
        this.stance = Stance.FREE;
        this.calculateStats();
        this.artefacts.addAll(set.getArtefacts());
        this.actionExecutor = FighterFactory.getActionDoer(String.valueOf(set.getId()),set.getCode());
    }

    private void calculateStats() {
        this.setMaxSpeed((int) (20-getLvl()*0.1));
        this.setMaxHp((int) (50+getLvl()*1.1));
        this.setMaxMana((int) (50+getLvl()*1.1));
        this.setMaxStamina((int) (50+getLvl()*1.1));
        this.setEvasion(5);
        this.setArmor(0);
        this.setRegenHp(1);
        this.setRegenMana(1);
        this.setRegenStamina(1);
        for(Artefact artefact:this.getArtefacts()){
            this.setMaxHp(this.getMaxHp()+artefact.getHpBoost());
            this.setMaxMana(this.getMaxMana()+artefact.getManaBoost());
            this.setMaxStamina(this.getMaxStamina()+artefact.getStaminaBoost());
            this.setEvasion(this.getEvasion()+artefact.getEvasionBoost());
            this.setArmor(this.getArmor()+artefact.getArmorBoost());
            this.setRegenHp(this.getRegenHp()+artefact.getHpRegenBoost());
            this.setRegenMana(this.getRegenMana()+artefact.getManaRegenBoost());
            //TODO
            this.setRegenStamina(this.getRegenStamina()/*+artefact.getStaminaRegenBoost()*/);
        }
        this.setCurHp(this.getMaxHp());
        this.setCurMana(this.getMaxMana());
        this.setCurStamina(this.getMaxStamina());
        this.setCurSpeed(this.getMaxSpeed());
    }

    public boolean isAlive(){
        return this.getCurHp()<=0;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public ActionExecutor getActionExecutor() {
        return actionExecutor;
    }

    public void setActionExecutor(ActionExecutor actionExecutor) {
        this.actionExecutor = actionExecutor;
    }

    public List<Artefact> getArtefacts() {
        return artefacts;
    }

    public void setArtefacts(List<Artefact> artefacts) {
        this.artefacts = artefacts;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public final int getCurHp() {
        return curHp;
    }

    public final void setCurHp(int curHp) {
        this.curHp = curHp;
    }

    public final int getCurSpeed() {
        return curSpeed;
    }

    public final void setCurSpeed(int curSpeed) { this.curSpeed = curSpeed;  }

    public Stance getStance() { return stance; }

    public void setStance(Stance stance) {this.stance = stance;}

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public int getRegenHp() {
        return regenHp;
    }

    public void setRegenHp(int regenHp) {
        this.regenHp = regenHp;
    }

    public int getRegenMana() {
        return regenMana;
    }

    public void setRegenMana(int regenMana) {
        this.regenMana = regenMana;
    }

    public int getRegenStamina() {
        return regenStamina;
    }

    public void setRegenStamina(int regenStamina) {
        this.regenStamina = regenStamina;
    }

    public int getEvasion() {
        return evasion;
    }

    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getCurMana() {
        return curMana;
    }

    public void setCurMana(int curMana) {
        this.curMana = curMana;
    }

    public int getCurStamina() {
        return curStamina;
    }

    public void setCurStamina(int curStamina) {
        this.curStamina = curStamina;
    }

    public final boolean rest(){
        setCurSpeed(getCurSpeed() - 1);
        return getCurSpeed() == 0;
    }
}

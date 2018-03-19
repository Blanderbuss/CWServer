package com.cw.models;



import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class FighterA implements Serializable{
    private static final long serialVersionUID = 1L;
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

    public enum Status {
        UNREGISTERED,
        REGISTERED,
        READY,
        FIGHTING
    }

    public enum State {
        FREE,
        DEFENDING
    }

    @Size(min=3, max=80)
    @NotNull
    private String name;
    @Min(0)
    @Max(80) // TODO revise
    private int lvl;

    @Size(min=3)
    @NotNull
    private String equipped;
    @NotNull
    private Status status = Status.UNREGISTERED;
    @NotNull
    private State state = State.FREE;
    @Min(0)
    private int maxHp;
    @Min(0)
    private int maxSpeed;
    @Min(0)
    private int curHp;
    @Min(0)
    private int curSpeed;

    public FighterA() {
    }

    /**
     * @param name is given through user input
     * @param lvl is given through getting it from user
     */
    public FighterA(String name, int lvl) {
        this.name = name;
        this.lvl = lvl;
        //Calculatin` initial stats
        setMaxSpeed((int) (20-getLvl()*0.1));
        setMaxHp(50+getLvl()*2);
        setCurHp(getMaxHp());
        setCurSpeed(getMaxSpeed());
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

    public final String getEquipped() {
        return equipped;
    }

    public final void setEquipped(String equipped) {
        this.equipped = equipped;
    }

    public final Status getStatus() {
        return status;
    }

    public final void setStatus(Status status) {
        this.status = status;
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

    public State getState() { return state; }

    public void setState(State state) {this.state = state;}

    public final boolean rest(){
        setCurSpeed(getCurSpeed() - 1);
        return getCurSpeed() == 0;
    }
}

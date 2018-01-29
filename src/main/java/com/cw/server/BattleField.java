package com.cw.server;

import com.cw.models.FighterA;

import java.util.ArrayList;
import java.util.Iterator;

public class BattleField implements Runnable{

    //TODO Count and set cur stats

    //TODO Method right after doAction for Blocking, chances, etc.

    private enum Status{
        INITIALIZED,
        FIGHTING,
        FINISHED
    }

    private Status status;

    private ArrayList<FighterA> fighters;

    public BattleField(ArrayList<FighterA> fighters){
        this.fighters = fighters;
        this.status = Status.INITIALIZED;
    }

    @Override
    public void run() {
        this.status = Status.FIGHTING;
        boolean notFinished = true;
        while(notFinished){
            Iterator<FighterA> iter = fighters.iterator();
            while (iter.hasNext() && notFinished) {
                FighterA cur = iter.next();
                if(cur.rest()) {
                    cur.doAction(fighters);
                    notFinished = allAlive();
                }
            }
        }
        this.status = Status.FINISHED;
        //TODO Out results of fight
    }

    boolean allAlive(){
        for (FighterA fighter:fighters) if(fighter.getCurHp() <= 0) return false;
        return true;
    }
}

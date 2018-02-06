package com.cw.server;

import com.cw.models.ActionResult;
import com.cw.models.FighterA;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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
                    //TODO client must get res
                    ActionResult res = calcAction(fighters.indexOf(cur),cur.doAction(fighters));
                    notFinished = finishCondition();
                }
            }
        }
        this.status = Status.FINISHED;
        //TODO Out results of fight
    }

    private ActionResult calcAction(int curIndex, FighterA.ActTarget at){
        FighterA.Action action = at.getAction();
        int targetIndex = at.getTarget();
        FighterA cur = fighters.get(curIndex);
        FighterA target = fighters.get(targetIndex);
        ActionResult res = new ActionResult();

        switch (action){

            case DEFEND:
                cur.setState(FighterA.State.DEFENDING);
                //TODO reduce stamina for cur
                fighters.set(curIndex,cur);
                res.set(true,"You are in defending position now");
                break;

            case ATTACK:
                Random rand = new Random();
                if(target.getState()==FighterA.State.DEFENDING){
                    if(rand.nextInt(10)<5){
                        res.result = false;
                        res.msg += "Your opponent blocked your attack\n";
                    }else{
                        res.msg += "Your opponent failed to block your attack\n";
                    }
                }
                if(res.result){
                    //TODO do something better with attack calculator
                    int dmg = cur.getLvl()*2+3;
                    target.setCurHp(target.getCurHp()-dmg);
                    //TODO reduce stamina for cur
                    fighters.set(curIndex,cur);
                    fighters.set(targetIndex,target);
                    res.msg += "You hit your opponent for " + dmg + " hp!";
                }
                break;
            default:
                res.result = false;
                res.msg = "Something went wrong";

        }

        return res;
    }

    private boolean finishCondition(){
        for (FighterA fighter:fighters) if(fighter.getCurHp() <= 0) return false;
        return true;
    }
}

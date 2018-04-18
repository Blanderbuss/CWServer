package com.cw.BattleLogic.Battlefield;

import com.cw.BattleLogic.Action.ActionImpl.ActionAttack;
import com.cw.BattleLogic.Action.ActionImpl.ActionDefend;
import com.cw.BattleLogic.Action.ActionImpl.ActionFree;
import com.cw.BattleLogic.Fighter;
import com.cw.BattleLogic.GameEnvironment;
import com.cw.factory.ActionExecutor;

import java.util.*;


public abstract class BattleFieldI implements Runnable {

    //TODO Method right after doAction for Blocking, chances, etc.

    protected ArrayList<Fighter> fighters;
    public Date date;
    private String result;

    protected abstract ArrayList<Fighter> getCurrentAllies(Fighter cur);

    protected abstract ArrayList<Fighter> getCurrentEnemies(Fighter cur);

    public Date getDate() {
        return date;
    }

    public ArrayList<Fighter> getFighters() {
        return fighters;
    }

    public void setFighters(ArrayList<Fighter> fighters) {
        this.fighters = fighters;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public BattleFieldI(ArrayList<Fighter> fighters) {
        this.date = new Date();
        System.out.println("Battlefield created");
        this.fighters = fighters;
    }

    @Override
    public void run() {
        System.out.println("Battlefield started");
        boolean notFinished = true;
        while (notFinished) {
            Iterator<Fighter> iter = fighters.iterator();
            while (iter.hasNext() && notFinished) {
                Fighter cur = iter.next();
                if (cur.rest()) {
                    ArrayList<Fighter> curAllies = getCurrentAllies(cur);
                    ArrayList<Fighter> curEnemies = getCurrentEnemies(cur);
                    GameEnvironment env = new GameEnvironment(getDate(), curAllies, curEnemies);
                    ActionExecutor curActionExecutor = cur.getActionExecutor();
                    Fighter.ActTarget curActTarget = curActionExecutor.doAction(cur, env);
                    String res = calcAction(cur, curActTarget);
                    //Adding new info to result
                    this.setResult(this.getResult()+res);
                    outAll();
                    notFinished = isToFinish();
                }
            }
        }
        //TODO Out results of fight
    }

    private String calcAction(Fighter cur, Fighter.ActTarget at) {
        Fighter.Action action = at.getAction();
        int targetIndex = at.getTarget();
        Fighter target = fighters.get(targetIndex);
        String res;

        switch (action) {
            case DEFEND:
                ActionDefend actionDefend = new ActionDefend(cur, target);
                res = actionDefend.perform();
                break;
            case ATTACK:
                ActionAttack actionAttack = new ActionAttack(cur, target);
                res = actionAttack.perform();
                break;
            case FREE:
                ActionFree actionFree = new ActionFree(cur, target);
                res = actionFree.perform();
                break;
            default:
                res= "ERROR: ILLEGAL ACTION";
        }
        return res;
    }

    void outAll() {
        for (Fighter fighter : fighters) {
            System.out.println("Fighter " + fighter.getName() + ": Stance[" + fighter.getStance() + "] HP[" + fighter.getCurHp() + "]");
        }
    }

    protected abstract boolean isToFinish();

}

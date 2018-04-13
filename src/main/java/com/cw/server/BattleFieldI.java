package com.cw.server;

import com.cw.BattleLogic.ActionResult;
import com.cw.BattleLogic.Fighter;
import com.cw.BattleLogic.GameEnvironment;
import com.cw.server.factory.ActionExecutor;

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
                    //TODO client must get res
                    ArrayList<Fighter> curAllies = getCurrentAllies(cur);
                    ArrayList<Fighter> curEnemies = getCurrentEnemies(cur);
                    GameEnvironment env = new GameEnvironment(getDate(), curAllies, curEnemies);
                    ActionExecutor curActionExecutor = cur.getActionExecutor();
                    Fighter.ActTarget curActTarget = curActionExecutor.doAction(cur, env);
                    ActionResult res = calcAction(cur, curActTarget);
                    System.out.println(res.msg);
                    outAll();
                    notFinished = isToFinish();
                }
            }
        }
        //TODO Out results of fight
    }

    private ActionResult calcAction(Fighter cur, Fighter.ActTarget at) {
        Fighter.Action action = at.getAction();
        int targetIndex = at.getTarget();
        Fighter target = fighters.get(targetIndex);
        ActionResult res = new ActionResult();

        switch (action) {

            case DEFEND:
                cur.setStance(Fighter.Stance.DEFENDING);
                cur.setCurSpeed(cur.getMaxSpeed());
                //TODO reduce stamina for cur
                //this.fighters.set(curIndex, cur);
                res.set(true, cur.getName() + " is in defending position now");
                break;

            case ATTACK:
                Random rand = new Random();
                if (target.getStance() == Fighter.Stance.DEFENDING) {
                    //TODO calculate blocking chances better
                    //TODO reduce stamina for cur
                    if (rand.nextInt(10) < 5) {
                        res.result = false;
                        res.msg += target.getName() + " attack of " + cur.getName();
                    } else {
                        res.msg += target.getName() + " failed to block attack of " + cur.getName() + "\n";
                    }
                }
                if (res.result) {
                    int dmg = cur.getLvl() * 2 + 3;
                    target.setCurHp(target.getCurHp() - dmg);
                    //TODO reduce stamina for cur
                    //fighters.set(curIndex, cur);
                    fighters.set(targetIndex, target);
                    res.msg += cur.getName() + " hit " + target.getName() + " for " + dmg + " hp!";
                }
                cur.setCurSpeed(cur.getMaxSpeed());
                break;
            default:
                res.result = false;
                res.msg = "Something went wrong";

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

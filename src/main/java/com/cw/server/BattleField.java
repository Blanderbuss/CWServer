package com.cw.server;

import com.cw.models.ActionResult;
import com.cw.models.Fighter;
import com.cw.models.GameEnvironment;
import com.cw.server.factory.ActionDoer;

import java.util.*;
import java.util.stream.Collectors;


public class BattleField implements Runnable {

    //TODO Count and set cur stats

    //TODO Method right after doAction for Blocking, chances, etc.

    private enum Status {
        INITIALIZED,
        FIGHTING,
        FINISHED
    }

    @Deprecated
    private Status status;

    private ArrayList<Fighter> fighters;
    private Date date;

    public Date getDate() {
        return date;
    }

    public BattleField(ArrayList<Fighter> fighters) {
        date = new Date();
        System.out.println("Battlefield created");
        this.fighters = fighters;
        this.status = Status.INITIALIZED;
    }

    @Override
    public void run() {
        System.out.println("Battlefield started");
        this.status = Status.FIGHTING;
        boolean notFinished = true;
        while (notFinished) {
            Iterator<Fighter> iter = fighters.iterator();
            while (iter.hasNext() && notFinished) {
                Fighter cur = iter.next();
                if (cur.rest()) {
                    //TODO client must get res
                    ArrayList<Fighter> curAllies = new ArrayList<Fighter>(Arrays.asList(cur));
                    ArrayList<Fighter> curEnemies = new ArrayList<Fighter>(fighters.stream().filter(f -> !f.equals(cur)).collect(Collectors.toList()));
                    GameEnvironment env = new GameEnvironment(getDate(), curAllies, curEnemies);
                    ActionDoer curActionDoer = cur.getActionDoer();
                    Fighter.ActTarget curActTarget = curActionDoer.doAction(cur, env);
                    ActionResult res = calcAction(fighters.indexOf(cur), curActTarget);
                    System.out.println(res.msg);
                    outAll();
                    notFinished = finishCondition();
                }
            }
        }
        this.status = Status.FINISHED;
        //TODO Out results of fight
    }

    private ActionResult calcAction(int curIndex, Fighter.ActTarget at) {
        Fighter.Action action = at.getAction();
        int targetIndex = at.getTarget();
        Fighter cur = fighters.get(curIndex);
        Fighter target = fighters.get(targetIndex);
        ActionResult res = new ActionResult();

        switch (action) {

            case DEFEND:
                cur.setState(Fighter.State.DEFENDING);
                //TODO calcute curSpeed from lvl
                cur.setCurSpeed(cur.getMaxSpeed());
                //TODO reduce stamina for cur
                fighters.set(curIndex, cur);
                res.set(true, cur.getName() + " is in defending position now");
                break;

            case ATTACK:
                Random rand = new Random();
                if (target.getState() == Fighter.State.DEFENDING) {
                    //TODO calculate blocking chances better
                    //TODO reduce stamina for cur !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    if (rand.nextInt(10) < 5) {
                        res.result = false;
                        res.msg += target.getName() + " attack of " + cur.getName();
                    } else {
                        res.msg += target.getName() + " failed to block attack of " + cur.getName() + "\n";
                    }
                }
                if (res.result) {
                    //TODO do something better with attack calculator?
                    int dmg = cur.getLvl() * 2 + 3;
                    target.setCurHp(target.getCurHp() - dmg);
                    //TODO reduce stamina for cur !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    fighters.set(curIndex, cur);
                    fighters.set(targetIndex, target);
                    res.msg += cur.getName() + " hit " + target.getName() + " for " + dmg + " hp!";
                }
                //TODO calcute curSpeed from lvl
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
            System.out.println("Fighter " + fighter.getName() + ": State[" + fighter.getState() + "] HP[" + fighter.getCurHp() + "]");
        }
    }

    private boolean finishCondition() {
        for (Fighter fighter : fighters)
            if (fighter.getCurHp() <= 0) {
                System.out.println("Fighter " + fighter.getName() + " is now dead");
                return false;
            }
        return true;
    }

}

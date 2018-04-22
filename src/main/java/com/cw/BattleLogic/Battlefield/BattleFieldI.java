package com.cw.BattleLogic.Battlefield;

import com.cw.BattleLogic.Action.ActionImpl.ActionAttack;
import com.cw.BattleLogic.Action.ActionImpl.ActionDefend;
import com.cw.BattleLogic.Action.ActionImpl.ActionFireball;
import com.cw.BattleLogic.Action.ActionImpl.ActionFree;
import com.cw.BattleLogic.Fighter;
import com.cw.BattleLogic.FighterI;
import com.cw.BattleLogic.GameEnvironment;
import com.cw.entities.Tuple;
import com.cw.factory.ActionExecutor;

import java.util.*;


public abstract class BattleFieldI implements Runnable {

    //TODO Method right after selectAction for Blocking, chances, etc.

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
                    Tuple<FighterI.Action, FighterI> curActTarget = curActionExecutor.selectAction(cur, env);
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

    private String calcAction(Fighter cur, Tuple<FighterI.Action, FighterI> actionTarget) {
        FighterI.Action action = actionTarget.val1;
        Fighter target = (Fighter) actionTarget.val2;
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
            case FIREBALL:
                ActionFireball actionFireball = new ActionFireball(cur, target);
                res = actionFireball.perform();
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
            System.out.println("Fighter " + fighter.getName() +
                    ": Stance[" + fighter.getStance()
                    + "] HP[" + fighter.getCurHp() + "/" +fighter.getMaxHp() + "]"
                    + "] STM[" + fighter.getCurStamina() + "/" +fighter.getMaxStamina() + "]"
                    + "] MNA[" + fighter.getCurMana() + "/" +fighter.getMaxMana() + "]"
            );
        }
    }

    protected abstract boolean isToFinish();

}

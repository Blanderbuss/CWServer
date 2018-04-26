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

    protected ArrayList<Fighter> fighters;
    private Date date;
    private StringBuilder result;
    private boolean isFinished;


    protected abstract ArrayList<Fighter> getCurrentAllies(Fighter cur);

    protected abstract ArrayList<Fighter> getCurrentEnemies(Fighter cur);

    public Date getDate() {
        return date;
    }

    public boolean IsFinished(){
        return isFinished;
    }

    public ArrayList<Fighter> getFighters() {
        return fighters;
    }

    public void setFighters(ArrayList<Fighter> fighters) {
        this.fighters = fighters;
    }

    public String getResult() {
        return result.toString();
    }

    private void addToResult(String text) {
        this.result.append(text);
    }

    public BattleFieldI(ArrayList<Fighter> fighters) {
        this.result = new StringBuilder("");
        this.date = new Date();
        this.fighters = fighters;
    }

    @Override
    public void run() {
        System.err.println("[INFO] BATTLEFIELD: Started");
        this.addToResult(fighterList());
        while (!isFinished) {
            Iterator<Fighter> iter = fighters.iterator();
            while (iter.hasNext() && !isFinished) {
                Fighter cur = iter.next();
                if (cur.rest()) {
                    ArrayList<Fighter> curAllies = getCurrentAllies(cur);
                    ArrayList<Fighter> curEnemies = getCurrentEnemies(cur);
                    GameEnvironment env = new GameEnvironment(getDate(), curAllies, curEnemies);
                    ActionExecutor curActionExecutor = cur.getActionExecutor();
                    Tuple<FighterI.Action, FighterI> curActTarget = curActionExecutor.selectAction(cur, env);

                    String res = calcAction(cur, curActTarget);
                    //Adding new info to result
                    this.addToResult(res);
                    //System.out.println(outAll());
                    isFinished = !isToFinish();
                }
            }
        }
        this.addToResult(fighterList());
        System.err.println("[INFO] BATTLEFIELD: Finished");
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

    String outAll() {
        StringBuilder res = new StringBuilder("");
        for (Fighter fighter : fighters) {
            res.append("Fighter " + fighter.getName() +
                    ": Stance[" + fighter.getStance()
                    + "] HP[" + fighter.getCurHp() + "/" +fighter.getMaxHp() + "]"
                    + "] STM[" + fighter.getCurStamina() + "/" +fighter.getMaxStamina() + "]"
                    + "] MNA[" + fighter.getCurMana() + "/" +fighter.getMaxMana() + "]\n"
            );
        }
        return res.toString();
    }

    private String fighterList(){
        StringBuilder res = new StringBuilder("");
        res.append("Fighter list:\n");
        for (Fighter fighter : fighters) {
            if(fighter.isDead())
                res.append("Fighter " + fighter.getName() + " is dead\n");
            res.append("Fighter " + fighter.getName() +
                        ": Stance[" + fighter.getStance()
                        + "] HP[" + fighter.getCurHp() + "/" +fighter.getMaxHp() + "]"
                        + "] STM[" + fighter.getCurStamina() + "/" +fighter.getMaxStamina() + "]"
                        + "] MNA[" + fighter.getCurMana() + "/" +fighter.getMaxMana() + "]\n"
            );
        }
        return res.toString();
    }

    protected abstract boolean isToFinish();

}

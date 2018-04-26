package com.cw.factory.impl;
import com.cw.BattleLogic.FighterI;
import com.cw.BattleLogic.GameEnvironment;
import com.cw.entities.Tuple;
import com.cw.factory.ActionExecutor;
public class ActionExecutorImpl implements ActionExecutor {
    public Tuple<FighterI.Action,FighterI> selectAction(FighterI self, GameEnvironment env) {
            FighterI enemy = env.getEnemies().get(0);
            if ((enemy.getStance() == FighterI.Stance.FREE && enemy.getCurHp() < enemy.getMaxHp() / 4) || (enemy.getStance() == FighterI.Stance.DEFENDING && enemy.getCurHp() < enemy.getMaxHp() / 4)) {
                if (enemy.getStance() == FighterI.Stance.DEFENDING && self.getCurStamina() < self.getMaxStamina() / 4) {
                    return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);
                } else if (self.getStance() == FighterI.Stance.DEFENDING) {
                    return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
                }
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK, enemy);
            } else if ((enemy.getStance() == FighterI.Stance.FREE && enemy.getCurHp() < enemy.getMaxHp() / 2) || (enemy.getStance() == FighterI.Stance.DEFENDING)) {
                if (enemy.getStance() == FighterI.Stance.DEFENDING && self.getCurStamina() < self.getMaxStamina() / 2) {
                    return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);
                } else if (self.getStance() == FighterI.Stance.DEFENDING) {
                    return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
                }
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FIREBALL, enemy);
            } else if (enemy.getStance() == FighterI.Stance.DEFENDING) {
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);
            }
            if (self.getStance() == FighterI.Stance.DEFENDING) {
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
            }
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FIREBALL, enemy);
    }
}

/*
public class ActionExecutorImpl implements ActionExecutor {
    public Tuple<FighterI.Action,FighterI> selectAction(FighterI self, GameEnvironment env) {
        FighterI enemy = env.getEnemies().get(0);
        if (enemy.getStance() == FighterI.Stance.FREE) {
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);
        } else if (enemy.getStance() == Fighter.Stance.DEFENDING) {
            if (self.getStance() == Fighter.Stance.DEFENDING) {
                return new Tuple<Fighter.Action, FighterI>(FighterI.Action.FREE, self);
            } else if (self.getCurMana() >= 75) {
                new Tuple<Fighter.Action, FighterI>(FighterI.Action.FIREBALL, env.getEnemies().get(0));
            }
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK, env.getEnemies().get(0));

        }
        return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK, env.getEnemies().get(0));
    }
}

//NOT USED ACTUALLY*/
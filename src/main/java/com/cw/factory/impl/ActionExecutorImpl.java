package com.cw.factory.impl;
import com.cw.BattleLogic.FighterI;
import com.cw.BattleLogic.GameEnvironment;
import com.cw.entities.Tuple;
import com.cw.factory.ActionExecutor;
public class ActionExecutorImpl implements ActionExecutor {
    public Tuple<FighterI.Action,FighterI> selectAction(FighterI self, GameEnvironment env) {
        FighterI enemy = env.getEnemies().get(0);
        ;
        if (enemy.getStance() == FighterI.Stance.FREE) {
            ;
            if (self.getStance() == FighterI.Stance.DEFENDING) {
                ;
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);

            } else if (self.getCurMana() >= 75) {
                ;
                new Tuple<FighterI.Action, FighterI>(FighterI.Action.FIREBALL, env.getEnemies().get(0));
                ;
            }
            ;
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK, env.getEnemies().get(0));

        } else if (enemy.getStance() == FighterI.Stance.DEFENDING) {
            ;
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);

        }
        ;
        return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK, env.getEnemies().get(0));
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
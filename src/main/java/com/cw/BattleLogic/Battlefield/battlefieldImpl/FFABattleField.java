package com.cw.BattleLogic.Battlefield.battlefieldImpl;

import com.cw.BattleLogic.Fighter;
import com.cw.BattleLogic.Battlefield.BattleFieldI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


public class FFABattleField extends BattleFieldI {

    public FFABattleField(ArrayList<Fighter> fighters) {
        super(fighters);
    }

    @Override
    protected boolean isToFinish() {
        int numberOfAlive = 0;
        for (Fighter fighter:fighters){
            if(fighter.isDead())numberOfAlive++;
            if(numberOfAlive==2)return false;
        }
        return true;
    }

    @Override
    protected ArrayList<Fighter> getCurrentAllies(Fighter cur) {
        return new ArrayList<Fighter>(Arrays.asList(cur));
    }

    @Override
    protected ArrayList<Fighter> getCurrentEnemies(Fighter cur) {
        //It just selects all other elements of the list
        return new ArrayList<Fighter>(fighters.stream().filter(f -> !f.equals(cur)).collect(Collectors.toList()));
    }
}

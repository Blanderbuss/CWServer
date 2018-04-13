package com.cw.server.battlefieldImpl;

import com.cw.BattleLogic.Fighter;
import com.cw.server.BattleFieldI;

import java.util.*;
import java.util.stream.Collectors;


public class DuelBattleField extends BattleFieldI {

    public DuelBattleField(ArrayList<Fighter> fighters) {
        super(fighters);
    }

    @Override
    protected boolean isToFinish() {
        for (Fighter fighter : fighters)
            if (fighter.isAlive()) {
                System.out.println("Fighter " + fighter.getName() + " is now dead");
                return false;
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

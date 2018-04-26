package com.cw.services;

import com.cw.entities.Set;


public interface FightServiceI {

    int readyForFight(Set set, String battleFieldType);

    String getResult(int indexOfBattleField, String typeOfBattleField);
}

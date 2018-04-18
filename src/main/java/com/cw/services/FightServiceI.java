package com.cw.services;

import com.cw.exceptions.FighterException;
import com.cw.entities.Set;


public interface FightServiceI {

    int readyForFight(Set set, String battleFieldType) throws FighterException;

    String getResult(int indexOfBattleField, String typeOfBattleField);
}

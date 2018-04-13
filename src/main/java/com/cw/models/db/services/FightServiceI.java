package com.cw.models.db.services;

import com.cw.exceptions.FighterException;
import com.cw.exceptions.UserException;
import com.cw.models.entities.Artefact;
import com.cw.models.entities.Set;
import com.cw.models.entities.User;

import java.util.List;


public interface FightServiceI {

    int readyForFight(Set set, String battleFieldType) throws FighterException;

    String getResult(int indexOfBattleField, String typeOfBattleField);
}

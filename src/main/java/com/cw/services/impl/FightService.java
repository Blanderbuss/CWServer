package com.cw.services.impl;

import com.cw.BattleLogic.Battlefield.BattleFieldI;
import com.cw.services.FightServiceI;
import com.cw.BattleLogic.Fighter;
import com.cw.entities.Set;
import com.cw.services.ArtefactServiceI;
import com.cw.services.BattleTypeServiceI;
import com.cw.services.SetServiceI;
import com.cw.services.UserServiceI;
import com.cw.BattleLogic.Battlefield.battlefieldImpl.DuelBattleField;
import com.cw.BattleLogic.Battlefield.battlefieldImpl.FFABattleField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class FightService implements FightServiceI {

    @Autowired
    private static ArtefactServiceI artefactService;

    @Autowired
    private static BattleTypeServiceI battleTypeServiceI;

    @Autowired
    private static SetServiceI setService;

    @Autowired
    private static UserServiceI userService;

    private ArrayList<DuelBattleField> duelBattleFields = new ArrayList<>();
    private ArrayList<FFABattleField> ffaBattleFields = new ArrayList<>();

    private static ArrayList<Fighter> duelQueue = new ArrayList<>();
    private static ArrayList<Fighter> ffaQueue = new ArrayList<>();

    private final static int NUMBER_OF_FIGHTERS_IN_DUEL=2;
    private final static int NUMBER_OF_FIGHTERS_IN_FFA=4;

    @Override
    public int readyForFight(Set set, String stringBattleFieldType) throws NoSuchElementException {
        Fighter fighter = new Fighter(set);
        switch (stringBattleFieldType){
            case "Duel":
                duelQueue.add(fighter);
                int duelIndex = duelBattleFields.size();
                if(duelQueue.size()==NUMBER_OF_FIGHTERS_IN_DUEL){
                    ArrayList<Fighter> fighters = new ArrayList<>(duelQueue);
                    DuelBattleField newDuelBattleField = new DuelBattleField(fighters);
                    duelBattleFields.add(newDuelBattleField);
                    new Thread(newDuelBattleField).start();
                    duelQueue.clear();
                }
                return duelIndex;
            case "FFA":
                ffaQueue.add(fighter);
                int ffaIndex = ffaBattleFields.size();
                if(ffaQueue.size()==NUMBER_OF_FIGHTERS_IN_FFA){
                    ArrayList<Fighter> fighters = new ArrayList<>(ffaQueue);
                    FFABattleField newFFABattleField = new FFABattleField(fighters);
                    ffaBattleFields.add(newFFABattleField);
                    new Thread(newFFABattleField).start();
                    ffaQueue.clear();
                }
                return ffaIndex;
            default:
                throw new NoSuchElementException();
        }
    }

    @Override
    public String getResult(int indexOfBattleField, String typeOfBattleField) {
        switch (typeOfBattleField){
            case "Duel":
                BattleFieldI selectedDuelBattleField = duelBattleFields.get(indexOfBattleField);
                if(!selectedDuelBattleField.IsFinished()) return "Battle is not finished";
                return selectedDuelBattleField.getResult();
            case "FFA":
                BattleFieldI selectedFfaBattleField = ffaBattleFields.get(indexOfBattleField);
                if(!selectedFfaBattleField.IsFinished()) return "Battle is not finished";
                return selectedFfaBattleField.getResult();
            default:
                throw new NoSuchElementException();
        }
    }

}

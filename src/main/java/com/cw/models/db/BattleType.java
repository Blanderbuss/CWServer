package com.cw.models.db;

public class BattleType {

    private long id;
    private long playersCount;
    private String battleground;

    public BattleType(long id, long playersCount, String battleground){
        this.id = id;
        this.playersCount = playersCount;
        this.battleground = battleground;
    }

}

package com.cw.models.db;

public class BattleType {

    private long id;
    private long playersCount;
    private String battleground;

    private void setId(long id) {
        this.id = id;
    }

    private void setPlayersCount(long playersCount) {
        this.playersCount = playersCount;
    }

    private void setBattleground(String battleground) {
        this.battleground = battleground;
    }

    public long getId() {

        return id;
    }

    public long getPlayersCount() {
        return playersCount;
    }

    public String getBattleground() {
        return battleground;
    }

    public BattleType(long id, long playersCount, String battleground){
        this.id = id;
        this.playersCount = playersCount;
        this.battleground = battleground;

    }

}

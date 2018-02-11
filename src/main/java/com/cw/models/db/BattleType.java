package com.cw.models.db;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BattleType {

    @NotNull
    @Min(0)
    private long id;
    @NotNull
    @Max(10)
    private long playersCount;
    @NotNull
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

package com.cw.models.db;

public class BattleType {

    private long id;

    public BattleType(long id){
        this.id = id;
    }

    // Getters must be here.
    public BattleType getBT(){
        return this;
    }

    public boolean update(long id){
        return true;
    }

    public boolean delete(long id){
        return true;
    }

    public long getQuantity(long id){
        return 0;
    }
}

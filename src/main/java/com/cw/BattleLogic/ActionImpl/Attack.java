package com.cw.BattleLogic.ActionImpl;

import com.cw.BattleLogic.ActionClassA;

public class Attack extends ActionClassA{

    private StringBuilder result;

    public Attack() {
        result = new StringBuilder(this.getActor().getName() + " tries to attack");
    }

    @Override
    protected int getHpChangeForTarget(){
        return 0;
    }

    @Override
    protected String report() {
        return result.toString();
    }
}

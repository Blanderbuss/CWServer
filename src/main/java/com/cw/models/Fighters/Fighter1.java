package com.cw.models.Fighters;

import com.cw.models.FighterA;

import java.util.ArrayList;

public class Fighter1 extends FighterA{


    @Override
    public actTarget doAction(ArrayList<FighterA> fighters) {
        return new actTarget(Action.DEFEND, null);
    }
}

package com.cw.server.factory;
import com.cw.models.FighterA;
import com.cw.models.GameEnvironment;

public interface ActionDoer {
    public void doAction(FighterA self, GameEnvironment env);
}

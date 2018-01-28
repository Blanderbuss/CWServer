package com.cw.models;

public interface FighterIF {

    enum Action {
        ATTACK,
        DEFEND
    }

    enum Status {
        UNREGISTERED,
        REGISTERED,
        READY,
        FIGHTING
    }

    void setStatus(Status status);

    //TODO Add parameters
    void doAction();

}

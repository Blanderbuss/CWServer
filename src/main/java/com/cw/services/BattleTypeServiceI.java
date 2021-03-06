package com.cw.services;

import com.cw.entities.BattleType;

/**
 * Created by denysmelnychenko on 2/11/18.
 */
public interface BattleTypeServiceI {

    BattleType getBattleTypeById(int id);

    boolean addBattleType(BattleType battleType);

    boolean updateBattleType(BattleType battleType);

    boolean deleteBattleTypeById(int id);
    BattleType deleteBattleType(BattleType battleType);

    void closeConnection();
}

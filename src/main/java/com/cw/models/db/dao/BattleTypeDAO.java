package com.cw.models.db.dao;

import com.cw.models.entities.BattleType;

/**
 * Created by Макс on 09.03.2018.
 */
public interface BattleTypeDAO {
    BattleType getBattleTypeById(int id);

    boolean addBattleType(BattleType battleType);

    boolean updateBattleType(BattleType battleType);

    boolean deleteBattleTypeById(int id);
    BattleType deleteBattleType(BattleType battleType);
}

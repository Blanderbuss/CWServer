package com.cw.db.dao;

import com.cw.entities.BattleType;

/**
 * Created by Макс on 09.03.2018.
 */
public interface BattleTypeDAO {
    BattleType getBattleTypeById(int id);

    boolean addBattleType(BattleType battleType);

    boolean updateBattleType(BattleType battleType);

    boolean deleteBattleTypeById(int id);
}

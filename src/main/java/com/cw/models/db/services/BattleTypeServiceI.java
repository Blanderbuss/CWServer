package com.cw.models.db.services;

import com.cw.models.entities.BattleType;
import org.springframework.stereotype.Service;

/**
 * Created by denysmelnychenko on 2/11/18.
 */
@Service
public interface BattleTypeServiceI {

    BattleType getBattleTypeById(int id);

    boolean addBattleType(BattleType battleType);

    boolean updateBattleType(BattleType battleType);

    boolean deleteBattleTypeById(int id);
    BattleType deleteBattleType(BattleType battleType);

    void closeConnection();
}

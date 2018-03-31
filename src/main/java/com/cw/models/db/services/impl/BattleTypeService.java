package com.cw.models.db.services.impl;

import com.cw.models.db.connectionFactory.ConnectionFactory;
import com.cw.models.db.dao.impl.JDBCBattleTypeDAO;
import com.cw.models.entities.BattleType;
import com.cw.models.db.services.BattleTypeServiceI;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Макс on 10.03.2018.
 */
@Service
public class BattleTypeService implements BattleTypeServiceI {
    private Connection connection = null;
    private JDBCBattleTypeDAO jdbcBattleTypeDAO = null;

    public BattleTypeService() {
        this.connection = ConnectionFactory.getConnection();
        this.jdbcBattleTypeDAO = new JDBCBattleTypeDAO(this.connection);
    }

    @Override
    public BattleType getBattleTypeById(int id) {
        return this.getBattleTypeById(id);
    }

    @Override
    public boolean addBattleType(BattleType battleType) {
        return this.addBattleType(battleType);
    }

    @Override
    public boolean updateBattleType(BattleType battleType) {
        return this.updateBattleType(battleType);
    }

    @Override
    public boolean deleteBattleTypeById(int id) {
        return this.deleteBattleTypeById(id);
    }

    @Override
    public BattleType deleteBattleType(BattleType battleType) {
        return this.deleteBattleType(battleType);
    }

    @Override
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

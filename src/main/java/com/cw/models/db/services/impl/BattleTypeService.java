package com.cw.models.db.services.impl;

import com.cw.models.db.connectionFactory.ConnectionFactory;
import com.cw.models.db.dao.impl.JDBCBattleTypeDAO;
import com.cw.models.entities.BattleType;
import com.cw.models.db.services.BattleTypeServiceI;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Макс on 10.03.2018.
 */
public class BattleTypeService implements BattleTypeServiceI {
    private Connection connection = null;
    private JDBCBattleTypeDAO jdbcBattleTypeDAO = null;

    public BattleTypeService() {
        this.connection = ConnectionFactory.getConnection();
        this.jdbcBattleTypeDAO = new JDBCBattleTypeDAO(this.connection);
    }

    @Override
    public BattleType getBattleTypeById(int id) {
        return this.jdbcBattleTypeDAO.getBattleTypeById(id);
    }

    @Override
    public boolean addBattleType(BattleType battleType) {
        return this.jdbcBattleTypeDAO.addBattleType(battleType);
    }

    @Override
    public boolean updateBattleType(BattleType battleType) {
        return this.jdbcBattleTypeDAO.updateBattleType(battleType);
    }

    @Override
    public boolean deleteBattleTypeById(int id) {
        return this.jdbcBattleTypeDAO.deleteBattleTypeById(id);
    }

    @Override
    public BattleType deleteBattleType(BattleType battleType) {
        this.deleteBattleTypeById(battleType.getId());
        return battleType;
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

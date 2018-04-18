package com.cw.services.impl;

import com.cw.db.connectionFactory.ConnectionFactory;
import com.cw.db.dao.impl.JDBCBattleTypeDAO;
import com.cw.entities.BattleType;
import com.cw.services.BattleTypeServiceI;
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

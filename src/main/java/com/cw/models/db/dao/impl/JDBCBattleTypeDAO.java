package com.cw.models.db.dao.impl;

import com.cw.models.db.dao.BattleTypeDAO;
import com.cw.models.entities.BattleType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Макс on 10.03.2018.
 */
public class JDBCBattleTypeDAO implements BattleTypeDAO {
    private Connection connection = null;

    public JDBCBattleTypeDAO(Connection connection) {
        this.connection = connection;
    }

    private static final String GET_BATTLETYPE_BY_ID_SQL = "SELECT * FROM `battles_type` WHERE `id` = ?";

    private static final String ADD_ARTIFACT_SQL = "INSERT INTO `battles_type` (`players_count`, `battleground`) VALUES (?, ?)";

    private static final String UPDATE_ARTIFACT_BY_ID_SQL = "UPDATE `battles_type` SET `players_count` = ?, `battleground` = ?";

    private static final String DELETE_ARTIFACT_BY_ID_SQL = "DELETE FROM `battles_type` WHERE `id` = ?";

    @Override
    public BattleType getBattleTypeById(int id) {
        //      this.connection = ConnectionFactory.getConnection();
        BattleType battleType = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCBattleTypeDAO.GET_BATTLETYPE_BY_ID_SQL);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            battleType = new BattleType(resultSet.getLong("players_count"), resultSet.getString("battleground"));
            battleType.setId(resultSet.getInt("id"));

            resultSet.close();
            preparedStatement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return battleType;
    }

    @Override
    public boolean addBattleType(BattleType battleType) {
//      this.connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCBattleTypeDAO.ADD_ARTIFACT_SQL);
            preparedStatement.setLong(1, battleType.getPlayersCount());
            preparedStatement.setString(2, battleType.getBattleground());

            preparedStatement.execute();
            preparedStatement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateBattleType(BattleType battleType) {
//        this.connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCBattleTypeDAO.UPDATE_ARTIFACT_BY_ID_SQL);
            preparedStatement.setLong(1, battleType.getPlayersCount());
            preparedStatement.setString(2, battleType.getBattleground());

            preparedStatement.execute();
            preparedStatement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteBattleTypeById(int id) {
//        this.connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCBattleTypeDAO.DELETE_ARTIFACT_BY_ID_SQL);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();
            preparedStatement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public BattleType deleteBattleType(BattleType battleType) {
//        this.connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCBattleTypeDAO.DELETE_ARTIFACT_BY_ID_SQL);
            preparedStatement.setInt(1, battleType.getId());

            preparedStatement.execute();
            preparedStatement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return battleType;
    }
}

package com.cw.models.db.dao.impl;

import com.cw.models.db.dao.ArtefactDAO;
import com.cw.models.entities.Artefact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Макс on 10.03.2018.
 */
public class JDBCArtefactDAO implements ArtefactDAO {
    private Connection connection = null;

    public JDBCArtefactDAO(Connection connection) {
        this.connection = connection;
    }

    private static final String GET_ARTEFACT_BY_ID_SQL = "SELECT * FROM `artefacts` WHERE `id` = ?";

    private static final String ADD_ARTIFACT_SQL = "INSERT INTO `artefacts` (`name`, `type`, `hp_boost`, `mana_boost`, `stamina_boost`, `hp_regen_boost`, `mana_regen_boost`, `evasion_boost`, `armor_boost`, `skin`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_ARTIFACT_BY_ID_SQL = "UPDATE `artefacts` SET `name` = ?, `type` = ?, `hp_boost` = ?, `mana_boost` = ?, `stamina_boost` = ?, `hp_regen_boost` = ?, `mana_regen_boost` = ?, `evasion_boost` = ?, `armor_boost` = ?, `skin` = ?";

    private static final String DELETE_ARTIFACT_BY_ID_SQL = "DELETE FROM `artefacts` WHERE `id` = ?";

    @Override
    public Artefact getArtefactById(int id) {
//      this.connection = ConnectionFactory.getConnection();
        Artefact artefact = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCArtefactDAO.GET_ARTEFACT_BY_ID_SQL);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            artefact = new Artefact(resultSet.getString("name"), resultSet.getString("type"), resultSet.getInt("hp_boost"), resultSet.getInt("mana_boost"), resultSet.getInt("stamina_boost"), resultSet.getInt("hp_regen_boost"), resultSet.getInt("mana_regen_boost"), resultSet.getInt("evasion_boost"), resultSet.getInt("armor_boost"), resultSet.getString("skin"));
            artefact.setId(resultSet.getInt("id"));

            resultSet.close();
            preparedStatement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artefact;
    }

    @Override
    public boolean addArtefact(Artefact artefact) {
//        this.connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCArtefactDAO.ADD_ARTIFACT_SQL);
            preparedStatement.setString(1, artefact.getName());
            preparedStatement.setString(2, artefact.getType());
            preparedStatement.setInt(3, artefact.getHpBoost());
            preparedStatement.setInt(4, artefact.getManaBoost());
            preparedStatement.setInt(5, artefact.getStaminaBoost());
            preparedStatement.setInt(6, artefact.getHpRegenBoost());
            preparedStatement.setInt(7, artefact.getManaRegenBoost());
            preparedStatement.setInt(8, artefact.getEvasionBoost());
            preparedStatement.setInt(9, artefact.getArmorBoost());
            preparedStatement.setString(10, artefact.getSkin());

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
    public boolean updateArtefact(Artefact artefact) {
//        this.connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCArtefactDAO.UPDATE_ARTIFACT_BY_ID_SQL);
            preparedStatement.setString(1, artefact.getName());
            preparedStatement.setString(2, artefact.getType());
            preparedStatement.setInt(3, artefact.getHpBoost());
            preparedStatement.setInt(4, artefact.getManaBoost());
            preparedStatement.setInt(5, artefact.getStaminaBoost());
            preparedStatement.setInt(6, artefact.getHpRegenBoost());
            preparedStatement.setInt(7, artefact.getManaRegenBoost());
            preparedStatement.setInt(8, artefact.getEvasionBoost());
            preparedStatement.setInt(9, artefact.getArmorBoost());
            preparedStatement.setString(10, artefact.getSkin());

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
    public boolean deleteArtefactById(int id) {
//        this.connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCArtefactDAO.DELETE_ARTIFACT_BY_ID_SQL);
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
    public Artefact deleteArtefact(Artefact artefact) {
        //        this.connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCArtefactDAO.DELETE_ARTIFACT_BY_ID_SQL);
            preparedStatement.setInt(1, artefact.getId());

            preparedStatement.execute();
            preparedStatement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artefact;
    }
}

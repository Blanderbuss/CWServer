package com.cw.db.dao.impl;

import com.cw.db.dao.ArtefactDAO;
import com.cw.entities.Artefact;
import com.cw.entities.Set;
import com.cw.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Макс on 10.03.2018.
 */

public class JDBCArtefactDAO implements ArtefactDAO {
    private Connection connection = null;

    public JDBCArtefactDAO(Connection connection) {
        this.connection = connection;
    }

    private static final String GET_ARTEFACT_BY_ID_SQL = "SELECT * FROM `artefacts` WHERE `id` = ?";

    private static final String GET_ARTEFACT_BY_SET_ID_SQL = "SELECT * FROM `artefact_in_set` INNER JOIN `artefacts` ON `artefact_in_set`.`id_artefact` = `artefacts`.`id` WHERE `artefact_in_set`.`id_set` = ?";
    private static final String GET_ARTEFACT_BY_USER_ID_SQL = "SELECT * FROM `backpack` INNER JOIN `artefacts` ON `backpack`.`artefact_id` = `artefacts`.`id` WHERE `backpack`.`user_id` = ?";

    private static final String ADD_ARTIFACT_SQL = "INSERT INTO `artefacts` (`name`, `type`, `hp_boost`, `mana_boost`, `stamina_boost`, `hp_regen_boost`, `mana_regen_boost`, `stamina_regen_boost`,attack_boost, `evasion_boost`, `armor_boost`, `skin`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String ADD_ARTIFACT_TO_USER_BACKPACK_SQL = "INSERT INTO `backpack` (`user_id`, `artefact_id`) VALUES (?, ?)";
    private static final String ADD_ARTIFACT_TO_SET_SQL = "INSERT INTO `artefact_in_set` (`id_set`, `id_artefact`) VALUES (?, ?)";

    private static final String UPDATE_ARTIFACT_BY_ID_SQL = "UPDATE `artefacts` SET `name` = ?, `type` = ?, `hp_boost` = ?, `mana_boost` = ?, `stamina_boost` = ?, `hp_regen_boost` = ?, `mana_regen_boost` = ?, `stamina_regen_boost` = ?,attack_boost = ?, `evasion_boost` = ?, `armor_boost` = ?, `skin` = ? WHERE `id` = ?";

    private static final String DELETE_ALL_ARTEFACTS_FROM_SET_SQL = "DELETE FROM `artefact_in_set` WHERE `artefact_in_set`.`id_set` = ?";

    private static final String DELETE_ARTIFACT_BY_ID_SQL = "DELETE FROM `artefacts` WHERE `id` = ?";

    @Override
    public Artefact getArtefactById(int id) {
//      this.connection = ConnectionFactory.getConnection();
        Artefact artefact = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCArtefactDAO.GET_ARTEFACT_BY_ID_SQL);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isNotEmpty = resultSet.next();
            if (isNotEmpty) {
                artefact = new Artefact(resultSet.getString("name"), resultSet.getString("type"), resultSet.getInt("hp_boost"), resultSet.getInt("mana_boost"), resultSet.getInt("stamina_boost"), resultSet.getInt("hp_regen_boost"), resultSet.getInt("mana_regen_boost"), resultSet.getInt("stamina_regen_boost"), resultSet.getInt("attack_boost"),resultSet.getInt("evasion_boost"), resultSet.getInt("armor_boost"), resultSet.getString("skin"));
                artefact.setId(resultSet.getInt("id"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artefact;
    }

    @Override
    public List<Artefact> getAllArtefactsBySetId(int id) {
        Artefact artefact = null;
        List<Artefact> artefacts = new ArrayList<Artefact>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCArtefactDAO.GET_ARTEFACT_BY_SET_ID_SQL);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                artefact = new Artefact(resultSet.getString("name"), resultSet.getString("type"), resultSet.getInt("hp_boost"), resultSet.getInt("mana_boost"), resultSet.getInt("stamina_boost"), resultSet.getInt("hp_regen_boost"), resultSet.getInt("mana_regen_boost"), resultSet.getInt("stamina_regen_boost"), resultSet.getInt("attack_boost"),resultSet.getInt("evasion_boost"), resultSet.getInt("armor_boost"), resultSet.getString("skin"));
                artefact.setId(resultSet.getInt("id_artefact"));

                artefacts.add(artefact);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artefacts;
    }

    @Override
    public List<Artefact> getAllArtefactByUserId(int id) {
        Artefact artefact = null;
        List<Artefact> artefacts = new ArrayList<Artefact>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCArtefactDAO.GET_ARTEFACT_BY_USER_ID_SQL);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                artefact = new Artefact(resultSet.getString("name"), resultSet.getString("type"), resultSet.getInt("hp_boost"), resultSet.getInt("mana_boost"), resultSet.getInt("stamina_boost"), resultSet.getInt("hp_regen_boost"), resultSet.getInt("mana_regen_boost"), resultSet.getInt("stamina_regen_boost"), resultSet.getInt("attack_boost"),resultSet.getInt("evasion_boost"), resultSet.getInt("armor_boost"), resultSet.getString("skin"));
                artefact.setId(resultSet.getInt("artefact_id"));
                artefacts.add(artefact);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artefacts;
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
            preparedStatement.setInt(8, artefact.getStaminaRegenBoost());
            preparedStatement.setInt(9, artefact.getAttackBoost());
            preparedStatement.setInt(10, artefact.getEvasionBoost());
            preparedStatement.setInt(11, artefact.getArmorBoost());
            preparedStatement.setString(12, artefact.getSkin());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating artefact failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    artefact.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating artefact failed, no ID obtained.");
                }
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addArtefactToUserBackpack(User user, Artefact artefact) {
//        this.connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCArtefactDAO.ADD_ARTIFACT_TO_USER_BACKPACK_SQL);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, artefact.getId());

            preparedStatement.execute();
            List<Artefact> newArtefacts = user.getUserArtefacts();
            newArtefacts.add(artefact);
            user.setUserArtefacts(newArtefacts);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addArtefactToSet(Set set, Artefact artefact) {
//        this.connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCArtefactDAO.ADD_ARTIFACT_TO_SET_SQL);
            preparedStatement.setInt(1, set.getId());
            preparedStatement.setInt(2, artefact.getId());

            preparedStatement.execute();
            List<Artefact> newArtefacts = set.getArtefacts();
            newArtefacts.add(artefact);
            set.setArtefacts(newArtefacts);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addArtefactsToSet(Set set, List<Artefact> artefacts) {
        if (artefacts.size() == 0 ) return true;
        String sql = "INSERT INTO `artefact_in_set` (`id_set`, `id_artefact`) VALUES";

        for (int i = 0; i < artefacts.size(); ++i) {
            sql += " (?, ?),";
        }
        sql = sql.substring(0, sql.length() - 1);

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            int indexes = 1;
            for (int i = 0; i < artefacts.size(); ++i) {
                preparedStatement.setInt(indexes, set.getId());
                preparedStatement.setInt(indexes + 1, artefacts.get(i).getId());
                indexes += 2;
            }

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteAllArtefactsFromSet(Set set) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(JDBCArtefactDAO.DELETE_ALL_ARTEFACTS_FROM_SET_SQL);
            preparedStatement.setInt(1, set.getId());

            preparedStatement.execute();
            preparedStatement.close();
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
            preparedStatement.setInt(8, artefact.getStaminaRegenBoost());
            preparedStatement.setInt(9, artefact.getAttackBoost());
            preparedStatement.setInt(10, artefact.getEvasionBoost());
            preparedStatement.setInt(11, artefact.getArmorBoost());
            preparedStatement.setString(12, artefact.getSkin());
            preparedStatement.setInt(13, artefact.getId());

            preparedStatement.execute();
            preparedStatement.close();
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
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

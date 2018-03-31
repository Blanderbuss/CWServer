package com.cw.models.db.services.impl;

import com.cw.models.db.connectionFactory.ConnectionFactory;
import com.cw.models.db.dao.impl.JDBCArtefactDAO;
import com.cw.models.entities.Artefact;
import com.cw.models.db.services.ArtefactServiceI;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Макс on 10.03.2018.
 */

@Service
public class ArtefactService implements ArtefactServiceI {
    private Connection connection = null;
    private JDBCArtefactDAO jdbcArtefactDAO = null;

    private static final String GET_ARTEFACT_BY_SET_ID_SQL = "SELECT * FROM `artefact_in_set` INNER JOIN `artefacts` ON `artefact_in_set`.`id_artefact` = `artefacts`.`id` WHERE `artefact_in_set`.`id_set` = ?";
    private static final String GET_ARTEFACT_BY_USER_ID_SQL = "SELECT * FROM `backpack` INNER JOIN `artefacts` ON `backpack`.`artefact_id` = `artefacts`.`id` WHERE `backpack`.`user_id` = ?";

    private static final String ADD_ARTIFACT_TO_USER_BACKPACK_SQL = "INSERT INTO `backpack` (`user_id`, `artefact_id`) VALUES (?, ?)";
    private static final String ADD_ARTIFACT_TO_SET_SQL = "INSERT INTO `artefact_in_set` (`id_set`, `id_artefact`) VALUES (?, ?)";

    public ArtefactService() {
        this.connection = ConnectionFactory.getConnection();
        this.jdbcArtefactDAO = new JDBCArtefactDAO(this.connection);
    }

    @Override
    public Artefact getArtefactById(int id) {
        return this.getArtefactById(id);
    }

    @Override
    public List<Artefact> getAllArtefactsBySetId(int id) {
        Artefact artefact = null;
        List<Artefact> artefacts = new ArrayList<Artefact>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(ArtefactService.GET_ARTEFACT_BY_SET_ID_SQL);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                artefact = new Artefact(resultSet.getString("name"), resultSet.getString("type"), resultSet.getInt("hp_boost"), resultSet.getInt("mana_boost"), resultSet.getInt("stamina_boost"), resultSet.getInt("hp_regen_boost"), resultSet.getInt("mana_regen_boost"), resultSet.getInt("evasion_boost"), resultSet.getInt("armor_boost"), resultSet.getString("skin"));
                artefact.setId(resultSet.getInt("id_artefact"));

                artefacts.add(artefact);
            }

            resultSet.close();
            preparedStatement.close();
            this.connection.close();
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
            PreparedStatement preparedStatement = this.connection.prepareStatement(ArtefactService.GET_ARTEFACT_BY_USER_ID_SQL);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                artefact = new Artefact(resultSet.getString("name"), resultSet.getString("type"), resultSet.getInt("hp_boost"), resultSet.getInt("mana_boost"), resultSet.getInt("stamina_boost"), resultSet.getInt("hp_regen_boost"), resultSet.getInt("mana_regen_boost"), resultSet.getInt("evasion_boost"), resultSet.getInt("armor_boost"), resultSet.getString("skin"));
                artefact.setId(resultSet.getInt("id_artefact"));

                artefacts.add(artefact);
            }

            resultSet.close();
            preparedStatement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artefacts;
    }

    @Override
    public boolean addArtefact(Artefact artefact) {
        return this.jdbcArtefactDAO.addArtefact(artefact);
    }

    @Override
    public boolean addArtefactToUserBackpack(int user_id, int artefact_id) {
//        this.connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(ArtefactService.ADD_ARTIFACT_TO_USER_BACKPACK_SQL);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, artefact_id);

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
    public boolean addArtefactToUserBackpack(int user_id, Artefact artefact) {
        return this.addArtefactToUserBackpack(user_id, artefact.getId());
    }

    @Override
    public boolean addArtefactToSet(int set_id, int artefact_id) {
//        this.connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(ArtefactService.ADD_ARTIFACT_TO_SET_SQL);
            preparedStatement.setInt(1, set_id);
            preparedStatement.setInt(2, artefact_id);

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
    public boolean addArtefactToSet(int set_id, Artefact artefact) {
        return this.addArtefactToSet(set_id, artefact.getId());
    }

    @Override
    public boolean addArtefactsToSet(int set_id, List<Integer> artefactsId) {
        String sql = "INSERT INTO `artefact_in_set` (`id_set`, `id_artefact`) VALUES";

        for (int i = 0; i < artefactsId.size(); ++i) {
            sql += " (?, ?),";
        }
        sql = sql.substring(0, sql.length() - 1);

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            int indexes = 1;
            for (int i = 0; i < artefactsId.size(); ++i) {
                preparedStatement.setInt(indexes, set_id);
                preparedStatement.setInt(indexes + 1, artefactsId.get(i));
                indexes += 2;
            }

            preparedStatement.execute();
            preparedStatement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean updateArtefact(Artefact artefact) {
        return this.jdbcArtefactDAO.updateArtefact(artefact);
    }

    @Override
    public boolean deleteArtefactById(int id) {
        return this.deleteArtefactById(id);
    }

    @Override
    public Artefact deleteArtefact(Artefact artefact) {
        return this.deleteArtefact(artefact);
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

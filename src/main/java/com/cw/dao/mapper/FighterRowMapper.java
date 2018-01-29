/*package com.cw.dao.mapper;

import codw.models.Fighter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FighterRowMapper implements RowMapper<Fighter> {

    @Override
    public Fighter mapRow(ResultSet resultSet, int i) throws SQLException {
        FighterResultSetExtractor extractor = new FighterResultSetExtractor();
        return extractor.extractData(resultSet);
    }
}
*/
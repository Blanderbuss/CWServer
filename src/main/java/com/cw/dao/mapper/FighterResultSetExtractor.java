package codw.dao.mapper;

import codw.models.Fighter;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FighterResultSetExtractor implements ResultSetExtractor<Fighter> {

    @Override
    public Fighter extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Fighter fighter = new Fighter();
        fighter.setName(resultSet.getString(1));
        fighter.setEquipped(resultSet.getString(2) + resultSet.getString(3));
        return fighter;
    }
}

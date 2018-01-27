package codw.dao;

import codw.dao.mapper.FighterRowMapper;
import codw.models.Fighter;
import codw.models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FighterDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Fighter> checkUser(User user){
        JdbcTemplate transform = new JdbcTemplate(dataSource);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("login", user.getLogin());
        parameters.put("pass", user.getPass());
        return transform.query("SELECT login, weapons.name, weapons.stats " +
                "FROM (users INNER JOIN usersweapons on users.id = usersweapons.userid " +
                "          ) INNER JOIN weapons on userweapons.weaponid = weapons.id " +
                "WHERE users.login = :login AND users.pass = :pass",new FighterRowMapper(),parameters);
    }
}

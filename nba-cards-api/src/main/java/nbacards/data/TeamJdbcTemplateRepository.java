package nbacards.data;

import nbacards.models.NbaCard;
import nbacards.models.Team;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TeamJdbcTemplateRepository implements TeamRepository {

    private final JdbcTemplate jdbcTemplate;
    private final  TeamMapper mapper = new TeamMapper();


    public TeamJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Team> findAll() {
        final String sql = "select team_id, team_name, city from team;";
        return jdbcTemplate.query(sql, ((resultSet, i) ->
                new Team(
                        resultSet.getInt("team_id"),
                        resultSet.getString("team_name"),
                        resultSet.getString("city")
                )
        ));

    }
    @Override
    @Transactional
    public Team findById(int id) {
        return findBy("where team_id = ?", id).stream().findFirst().orElse(null);
    }

    private List<Team> findBy(String where, Object param) {
        final String sql = "select team_id, team_name, city " + "from team " +
                where;

        return jdbcTemplate.query(sql, mapper, param);
    }

}

package nbacards.data;

import nbacards.models.Team;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamJdbcTemplateRepository implements TeamRepository {

    private final JdbcTemplate jdbcTemplate;

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

}

package nbacards.data;

import nbacards.models.NbaCard;
import nbacards.models.Team;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamMapper implements RowMapper<Team> {
    @Override
    public Team mapRow(ResultSet resultSet, int i) throws SQLException {
        Team team = new Team();
        team.setTeamId(resultSet.getInt("team_id"));
        team.setTeamName(resultSet.getString("team_name"));
        team.setCityName(resultSet.getString("city"));
        return team;

    }

}

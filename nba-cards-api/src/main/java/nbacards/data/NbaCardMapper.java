package nbacards.data;

import nbacards.models.NbaCard;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NbaCardMapper implements RowMapper<NbaCard> {

    @Override
    public NbaCard mapRow(ResultSet resultSet, int i) throws SQLException {
        NbaCard card = new NbaCard();
        card.setCardId(resultSet.getInt("card_id"));
        card.setName(resultSet.getString("name"));
        card.setPosition(resultSet.getString("position"));
        card.setTeamId(resultSet.getInt("team_id"));
        card.setPpg(resultSet.getDouble("ppg"));
        card.setApg(resultSet.getDouble("apg"));
        card.setRpg(resultSet.getDouble("rpg"));
        return card;

    }

}

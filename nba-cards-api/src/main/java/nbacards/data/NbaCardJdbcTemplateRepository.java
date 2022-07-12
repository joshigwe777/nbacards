package nbacards.data;

import nbacards.models.NbaCard;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class NbaCardJdbcTemplateRepository implements NbaCardRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NbaCardMapper mapper = new NbaCardMapper();

    public NbaCardJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate  = jdbcTemplate;
    }

    @Override
    public List<NbaCard> findAll() {
        final String sql = "select card_id, `name`, image_url, team_id, " +
                "ppg, apg, rpg, position " +
                "from nba_card;";
        return jdbcTemplate.query(sql, mapper);
    }
    @Override
    @Transactional
    public NbaCard findById(int id) {
        return findBy("where card_id = ?;", id).stream()
                .findFirst()
                .orElse(null);
    }
    @Override
    public List<NbaCard> findByPosition(String position) {
        return findBy("where `position` like concat('%', ?, '%');", position);
    }
    @Override
    @Transactional
    public List<NbaCard> findByTeam(int teamId) {
        return findBy("where team_id = ?;", teamId);
    }
    @Override
    public NbaCard add(NbaCard card) {
        final String sql = "insert into nba_card (`name`, image_url, publisher_id, weight, minimum_players, maximum_players) " +
                "values (?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, card.getName());
            ps.setString(2, card.getImgUrl());
            ps.setInt(3, card.getTeamId());
            ps.setString(4, card.getPosition());
            ps.setDouble(5, card.getPpg());
            ps.setDouble(5, card.getApg());
            ps.setDouble(5, card.getRpg());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        card.setCardId(keyHolder.getKey().intValue());
        return card;
    }
    @Override
    @Transactional
    public boolean update(NbaCard card) {
        final String sql = "update nba_card set "
                + "`name` = ?, "
                + "image_url = ?, "
                + "team_id = ?, "
                + "position = ?, "
                + "ppg = ?, "
                + "apg = ?, "
                + "rpg = ? "
                + "where card_id = ?;";
        return jdbcTemplate.update(sql,
                card.getName(),
                card.getImgUrl(),
                card.getTeamId(),
                card.getPosition(),
                card.getPpg(),
                card.getApg(),
                card.getRpg(),
                card.getCardId()) > 0;
    }
    @Override
    @Transactional
    public boolean deleteById(int id) {
        final String sql = "delete from nba_card where card_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }


    private List<NbaCard> findBy(String where, Object param) {
        final String sql = "select card_id, `name`, image_url, team_id, " +
                "ppg, apg, rpg, position " +
                "from nba_card " + where;

        return jdbcTemplate.query(sql, mapper, param);
    }




}

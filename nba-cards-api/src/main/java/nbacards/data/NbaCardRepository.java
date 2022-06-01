package nbacards.data;

import nbacards.models.NbaCard;

import java.util.List;
import java.util.Map;

public interface NbaCardRepository {

    List<NbaCard> findAll();

    NbaCard findById(int id);

    List<NbaCard> findByPosition(String position);

    List<NbaCard> findByTeam(int teamId);

    NbaCard add(NbaCard card);

    boolean update(NbaCard card);

    boolean deleteById(int id);


}

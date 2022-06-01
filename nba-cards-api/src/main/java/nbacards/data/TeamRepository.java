package nbacards.data;

import nbacards.models.Team;
import java.util.List;

public interface TeamRepository {
    List<Team> findAll();
}

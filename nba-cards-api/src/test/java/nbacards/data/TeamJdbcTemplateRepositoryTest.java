package nbacards.data;

import nbacards.models.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeamJdbcTemplateRepositoryTest {

    @Autowired
    TeamRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Team> teamList = repository.findAll();
        assertTrue(teamList.size()>=3);
    }
    @Test
    void shouldFindByLakersId() {
        Team lakers = repository.findById(1);
        assertEquals(1, lakers.getTeamId());
    }
}
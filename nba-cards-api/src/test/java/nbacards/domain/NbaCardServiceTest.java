package nbacards.domain;

import nbacards.data.NbaCardRepository;
import nbacards.models.NbaCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NbaCardServiceTest {

    @MockBean
    NbaCardRepository repository;

    @Autowired
    NbaCardService service;

    @Test
    void shouldFindAll() {

        List<NbaCard> cards = List.of(
                new NbaCard(1,"Lebron James",27.0, 7.2,7.3,"Small forward","www.google.com",1),
                new NbaCard(1,"Luka",27.0, 7.2,7.3,"Point Guard","www.google.com",2),
                new NbaCard(1,"Steph",27.0, 7.2,7.3,"Point Guard","www.google.com",3)
        );
        when(repository.findAll()).thenReturn(cards);

        List<NbaCard> actual = service.findAll();
        assertEquals(3,actual.size());
        assertEquals("Lebron James",actual.get(0).getName());
    }

    @Test
    void findByPosition() {
        var card = new NbaCard(1,"Luka",27.0, 7.2,7.3,"Point Guard","www.google.com",2);

        when(repository.findByPosition("Point Guard")).thenReturn((List<NbaCard>) card);

        List<NbaCard> actual = repository.findByPosition("Point Guard");
        assertNotNull(actual);
        assertEquals(actual.get(0).getName(), "Luka");
    }

    @Test
    void findByTeam() {
        var card = new NbaCard(1,"Luka",27.0, 7.2,7.3,"Point Guard","www.google.com",2);

        when(repository.findByPosition("Point Guard")).thenReturn((List<NbaCard>) card);

        List<NbaCard> actual = repository.findByPosition("Point Guard");
        assertNotNull(actual);
        assertEquals(actual.get(0).getName(), "Luka");
    }

    @Test
    void findById() {
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}
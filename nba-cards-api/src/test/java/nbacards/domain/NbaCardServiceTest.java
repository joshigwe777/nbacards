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
    void shouldFindByPosition() {
        var card = new NbaCard(1,"Luka",27.0, 7.2,7.3,"Point Guard","www.google.com",2);

        when(repository.findByPosition("Point Guard")).thenReturn((List<NbaCard>) card);

        List<NbaCard> actual = repository.findByPosition("Point Guard");
        assertNotNull(actual);
        assertEquals(actual.get(0).getName(), "Luka");
    }
    @Test
    void shouldNotFindByPosition() {
        var card = new NbaCard(1,"Luka",27.0, 7.2,7.3,"Point Guard","www.google.com",2);

        when(repository.findByPosition("Shooting Guard")).thenReturn((List<NbaCard>) card);

        List<NbaCard> actual = repository.findByPosition("Shooting Guard");
        assertEquals(0, actual.size());
    }

    @Test
    void shouldFindByTeam() {
        var card = new NbaCard(1,"Luka",27.0, 7.2,7.3,"Point Guard","www.google.com",2);

        when(repository.findByTeam(2)).thenReturn((List<NbaCard>) card);

        List<NbaCard> actual = repository.findByTeam(2);
        assertNotNull(actual);
        assertEquals(actual.get(0).getName(), "Luka");
    }
    @Test
    void shouldNotFindByTeam() {
        var card = new NbaCard(1,"Luka",27.0, 7.2,7.3,"Point Guard","www.google.com",2);

        when(repository.findByTeam(2)).thenReturn((List<NbaCard>) card);
        List<NbaCard> actual = repository.findByTeam(3);
        assertEquals(0, actual.size());
    }

    @Test
    void shouldFindById() {
        var card = new NbaCard(1,"Luka",27.0, 7.2,7.3,"Point Guard","www.google.com",2);

        when(repository.findById(1)).thenReturn((card));

        NbaCard actual = repository.findById(1);
        assertEquals("Luka", actual.getName());
    }
    @Test
    void shouldNotFindById() {
        var card = new NbaCard(1,"Luka",27.0, 7.2,7.3,"Point Guard","www.google.com",2);

        when(repository.findById(1)).thenReturn((card));

        NbaCard actual = repository.findById(2);
        assertNull(actual);
    }

    @Test
    void shouldAddValidCard() {
        var card = new NbaCard(1,"Luka",27.0, 7.2,7.3,"Point Guard","www.google.com",2);

        when(repository.add(card)).thenReturn(card);

        Result actual = service.add(card);

        assertTrue(actual.isSuccess());
        assertNotNull(actual.getPayload());

    }
    @Test
    void shouldNotAddCardWithBlankName() {

    }
    @Test
    void shouldNotAddNullCard() {

    }
    @Test
    void shouldNotAddCardWithNegativePoints() {

    }
    @Test
    void shouldNotAddCardWithNegativeRebounds() {

    }
    @Test
    void shouldNotAddCardWithNegativeAssists() {

    }
    @Test
    void shouldNotAddCardWithInvalidImageUrl() {

    }
    @Test
    void shouldNotAddCardWithoutTeam() {

    }

    @Test
    void shouldUpdateValidCard() {
    }
    @Test
    void shouldNotUpdateCardThatDoesNotExist() {

    }

    @Test
    void shouldDeleteById() {
    }
    @Test
    void shouldNotDeleteNonexistentCard() {
    }
}
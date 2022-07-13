package nbacards.domain;

import nbacards.data.NbaCardRepository;
import nbacards.models.NbaCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
        List<NbaCard> cards = new ArrayList<NbaCard>() ;
        cards.add(card);
        when(repository.findByPosition("Point Guard")).thenReturn(cards);

        List<NbaCard> actual = repository.findByPosition("Point Guard");
        assertNotNull(actual);
        assertEquals(actual.get(0).getName(), "Luka");
    }
    @Test
    void shouldNotFindByPosition() {
        var card = new NbaCard(1,"Luka",27.0, 7.2,7.3,"Point Guard","www.google.com",2);
        List<NbaCard> cards = new ArrayList<NbaCard>() ;
        cards.add(card);
        when(repository.findByPosition("Point Guard")).thenReturn(cards);

        List<NbaCard> actual = repository.findByPosition("Shooting Guard");
        assertEquals(0, actual.size());
    }

    @Test
    void shouldFindByTeam() {
        var card = new NbaCard(1,"Luka",27.0, 7.2,7.3,"Point Guard","www.google.com",2);
        List<NbaCard> cards = new ArrayList<NbaCard>() ;
        cards.add(card);
        when(repository.findByTeam(2)).thenReturn((cards));

        List<NbaCard> actual = repository.findByTeam(2);
        assertNotNull(actual);
        assertEquals(actual.get(0).getName(), "Luka");
    }
    @Test
    void shouldNotFindByTeam() {
        var card = new NbaCard(1,"Luka",27.0, 7.2,7.3,"Point Guard","www.google.com",2);
        List<NbaCard> cards = new ArrayList<NbaCard>() ;
        cards.add(card);

        when(repository.findByTeam(2)).thenReturn(cards);
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
        NbaCard card = new NbaCard(1,"Luka",27.0, 7.2,7.3,"Point Guard","www.google.com",2);
        card.setCardId(0);
        when(repository.add(card)).thenReturn(card);

        Result actual = service.add(card);

        assertTrue(actual.isSuccess());
        assertNotNull(actual.getPayload());

    }
    @Test
    void shouldNotAddCardWithBlankName() {
        NbaCard card = new NbaCard(1,"",27.0, 7.2,7.3,"Point Guard","www.google.com",2);
         Result<NbaCard> result = service.add(card);
         assertFalse(result.isSuccess());
         assertEquals(result.getMessages().size(),1);
         assertEquals("'name' is required.",result.getMessages().get(0));

    }
    @Test
    void shouldNotAddNullCard() {
        NbaCard card = null;
        Result<NbaCard> result = service.add(card);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessages().size(),1);
        assertEquals("'card' cannot be null.",result.getMessages().get(0));

    }
    @Test
    void shouldNotAddCardWithNegativePoints() {
        NbaCard card = new NbaCard(1,"Luka",-27.0, 7.2,7.3,"Point Guard","www.google.com",2);
        Result<NbaCard> result = service.add(card);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessages().size(),1);
        assertEquals("'points per game cannot be less than zero",result.getMessages().get(0));

    }
    @Test
    void shouldNotAddCardWithNegativeRebounds() {
        NbaCard card = new NbaCard(1,"Luka",27.0, 7.2,-7.3,"Point Guard","www.google.com",2);
        Result<NbaCard> result = service.add(card);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessages().size(),1);
        assertEquals("rebounds per game cannot be less than zero",result.getMessages().get(0));

    }
    @Test
    void shouldNotAddCardWithNegativeAssists() {
        NbaCard card = new NbaCard(1,"Luka",27.0, -7.2,7.3,"Point Guard","www.google.com",2);
        Result<NbaCard> result = service.add(card);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessages().size(),1);
        assertEquals("assists per game cannot be less than zero",result.getMessages().get(0));

    }
    @Test
    void shouldNotAddCardWithInvalidImageUrl() {
        NbaCard card = new NbaCard(1,"Luka",27.0, 7.2,7.3,"Point Guard","invalid",2);
        Result<NbaCard> result = service.add(card);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessages().size(),1);
        assertEquals("'imageUrl' is required.",result.getMessages().get(0));

    }
    @Test
    void shouldNotAddCardWithoutTeam() {
        NbaCard card = new NbaCard(1,"Luka",27.0, 7.2,7.3,"Point Guard","www.google.com",-1);
        Result<NbaCard> result = service.add(card);
        assertFalse(result.isSuccess());
        assertEquals(result.getMessages().size(),1);
        assertEquals("team is required",result.getMessages().get(0));

    }

    @Test
    void shouldUpdateValidCard() {
        NbaCard card = new NbaCard(1,"Luka",27.0, 7.2,7.3,"Point Guard","www.google.com",1);
        when(repository.update(card)).thenReturn(true);

        Result<NbaCard> result = service.update(card);

        assertTrue(result.isSuccess());

    }
    @Test
    void shouldNotUpdateCardThatDoesNotExist() {
        NbaCard card = new NbaCard(1,"Wilt",50.0, 7.2,7.3,"Center","www.google.com",1);

        Result<NbaCard> result = service.update(card);

        assertFalse(result.isSuccess());
        assertEquals(ResultType.NOT_FOUND, result.getResultType());

    }
    @Test
    void shouldNotUpdateNullCard() {
        NbaCard card = null;

        Result<NbaCard> result = service.update(card);
        assertFalse(result.isSuccess());

    }

    @Test
    void shouldDeleteById() {
        when(repository.deleteById(1)).thenReturn(true);

        Result<Void> result = service.deleteById(1);
        assertTrue(result.isSuccess());

    }
    @Test
    void shouldNotDeleteNonexistentCard() {
        Result<Void> actual = service.deleteById(1);
        assertFalse(actual.isSuccess());
        assertEquals(ResultType.NOT_FOUND, actual.getResultType());
    }
}
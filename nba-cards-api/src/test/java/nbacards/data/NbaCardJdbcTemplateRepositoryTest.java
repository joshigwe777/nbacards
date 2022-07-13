package nbacards.data;

import nbacards.models.NbaCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NbaCardJdbcTemplateRepositoryTest {

    @Autowired
    NbaCardRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAllCards() {
        List<NbaCard> cards = repository.findAll();
        assertTrue(cards.size()>=3);
    }

    @Test
    void shouldFindLebronById() {
        NbaCard lebron = repository.findById(1);
        assertEquals("Lebron James", lebron.getName());
    }

    @Test
    void shouldNotFindKobeWhoIsAnInvalidPlayer() {

        NbaCard kobe = repository.findById(5);
        assertEquals(null, kobe);

    }

    @Test
    void shouldFindLukaDoncicByPosition() {
        List<NbaCard> luka = repository.findByPosition("Point Guard");
        assertTrue(luka.stream().anyMatch(c->c.getName().equalsIgnoreCase("Luka Doncic")));
    }

    @Test
    void shouldNotFindChrisPaulByPosition() {
        List<NbaCard> chris = repository.findByPosition("pg");
        assertFalse(chris.stream().anyMatch(c->c.getName().equalsIgnoreCase("Chris Paul")));
    }

    @Test
    void shouldFindLebronByTeam() {
        List<NbaCard> lebron = repository.findByTeam(1);
        assertTrue(lebron.stream().anyMatch(c->c.getName().equalsIgnoreCase("Lebron James")));
    }

    @Test
    void shouldNotFindKobeByTeam() {
        List<NbaCard> kobe = repository.findByTeam(1);
        assertFalse(kobe.stream().anyMatch(c->c.getName().equalsIgnoreCase("Kobe Bryant")));

    }

    @Test
    void shouldAddSteph() {
        NbaCard stephCurry = new NbaCard();
        stephCurry.setPpg(25.0);
        stephCurry.setPosition("pg");
        stephCurry.setName("Stephen Curry");
        stephCurry.setImgUrl("www.google.com");
        stephCurry.setTeamId(2);
        stephCurry.setApg(6.0);
        stephCurry.setRpg(5.5);

        NbaCard added = repository.add(stephCurry);
        assertEquals(added,stephCurry);

    }

    @Test
    void shouldUpdateLebron() {
        NbaCard lebronJames = new NbaCard();
        lebronJames.setPpg(27.0);
        lebronJames.setCardId(1);
        lebronJames.setPosition("sf");
        lebronJames.setName("Lebron James");
        lebronJames.setTeamId(1);
        lebronJames.setApg(6.0);
        lebronJames.setRpg(5.5);


        assertTrue(repository.update(lebronJames));

    }
    @Test
    void shouldNotUpdateWithInvalidCardId() {
        NbaCard lebronJames = new NbaCard();
        lebronJames.setPpg(27.0);
        lebronJames.setCardId(1);
        lebronJames.setPosition("sf");
        lebronJames.setName("Lebron James");
        lebronJames.setTeamId(1);
        lebronJames.setApg(6.0);
        lebronJames.setRpg(5.5);


        assertFalse(repository.update(lebronJames));

    }

    @Test
    void shouldDeleteLukaById() {

        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));

    }
}
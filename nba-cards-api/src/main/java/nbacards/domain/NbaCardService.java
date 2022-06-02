package nbacards.domain;

import nbacards.data.NbaCardRepository;
import nbacards.models.NbaCard;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class NbaCardService {
    private final NbaCardRepository repository;
    private final Validator validator;


    public NbaCardService(NbaCardRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public List<NbaCard> findAll() {
        return repository.findAll();
    }

    public List<NbaCard> findByPosition(String position) {
        return repository.findByPosition(position);
    }

    public List<NbaCard> findByTeam(int teamId) {
        return repository.findByTeam(teamId);
    }

    public NbaCard findById(int cardId) {
        return repository.findById(cardId);
    }
    public Result<NbaCard> add(NbaCard card) {
        return null;
    }

    public Result<NbaCard> update(NbaCard card) {
        return null;
    }

    public Result<Void> deleteById(int id) {
        Result<Void> result = new Result<>();
        if(!repository.deleteById(id)) {
            result.addMessage(notFoundMessage(id), ResultType.INVALID);
        }
        return result;
    }

    private Result<NbaCard> validate(NbaCard card) {
        Result<NbaCard> result = new Result();

        if(card == null) {
            result.addMessage("'card' cannot be null", ResultType.INVALID);
        }

        Set<ConstraintViolation<NbaCard>> violations = validator.validate(card);
        for (ConstraintViolation<NbaCard> violation: violations) {
            result.addMessage(violation.getMessage(), ResultType.INVALID);
        }
        return result;
    }

    private String notFoundMessage(int id) {
        return String.format("Card with id: '%s' was not found.", id);
    }
}

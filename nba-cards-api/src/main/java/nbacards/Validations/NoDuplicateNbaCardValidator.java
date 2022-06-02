package nbacards.Validations;

import nbacards.data.NbaCardRepository;
import nbacards.models.NbaCard;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NoDuplicateNbaCardValidator implements ConstraintValidator<NoDuplicateNbaCard, NbaCard> {

    private final NbaCardRepository repository;


    public NoDuplicateNbaCardValidator(NbaCardRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(NbaCard card, ConstraintValidatorContext context) {
        return !isDuplicate(card);
    }

    private boolean isDuplicate(NbaCard card) {
        List<NbaCard> cards = repository.findAll();
        for (NbaCard c: cards) {
            if (c.getCardId()!= card.getCardId()
                && c.getName().equals(card.getName())
                    && c.getPosition().equals(card.getPosition())
            ) {
                return true;

            }
        }
        return false;
    }
}

package nbacards.Validations;

import nbacards.data.NbaCardRepository;
import nbacards.models.NbaCard;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class PositionCannotBeInvalidValidator implements ConstraintValidator<PositionCannotBeInvalid, NbaCard> {

    private final NbaCardRepository repository;

    public PositionCannotBeInvalidValidator(NbaCardRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(NbaCard card, ConstraintValidatorContext context) {
        return hasValidPosition(card);

    }

    private boolean hasValidPosition(NbaCard card) {
        if(card.getPosition().equalsIgnoreCase("pg")||
                card.getPosition().equalsIgnoreCase("sg") ||
                card.getPosition().equalsIgnoreCase("sf") ||
                card.getPosition().equalsIgnoreCase("pf") ||
                card.getPosition().equalsIgnoreCase("c")
        ) {
            return true;
        }
        return false;
    }
}

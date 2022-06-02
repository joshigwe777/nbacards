package nbacards.Validations;

import nbacards.data.NbaCardRepository;
import nbacards.models.NbaCard;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


public class StatsCannotBeLessThanZeroValidator implements ConstraintValidator<StatsCannotBeLessThanZero, NbaCard> {

    private final NbaCardRepository repository;


    public StatsCannotBeLessThanZeroValidator(NbaCardRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(NbaCard card, ConstraintValidatorContext context) {
        return statsAreValid(card);
    }

    private boolean statsAreValid(NbaCard card) {
        if(card.getPpg()>=0 &&
                card.getApg()>=0 &&
                card.getRpg()>=0) {
            return true;

        }
        return false;
    }
}

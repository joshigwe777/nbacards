package nbacards.Validations;

import nbacards.models.NbaCard;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameCannotBeBlankValidator implements ConstraintValidator<NameCannotBeBlank, NbaCard>{
    @Override
    public boolean isValid(NbaCard card, ConstraintValidatorContext constraintValidatorContext) {
        if(card.getName()=="" || card.getName()==null) {
            return false;
        }
        return true;
    }
}

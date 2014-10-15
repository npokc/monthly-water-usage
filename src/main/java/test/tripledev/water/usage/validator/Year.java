package test.tripledev.water.usage.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = YearConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Year {

    String message() default "{Year}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

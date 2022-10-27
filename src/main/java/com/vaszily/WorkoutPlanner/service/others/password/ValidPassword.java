package com.vaszily.WorkoutPlanner.service.others.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PasswordValidator.class})
public @interface ValidPassword {
    String message() default "Password must contain at least lower case character, upper case character, numeric character, special symbol(@,#,$,%,&) and length should be between 8 and 20!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

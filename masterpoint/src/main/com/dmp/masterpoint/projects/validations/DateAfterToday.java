package com.dmp.masterpoint.projects.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DateAfterTodayValidator.class)
public @interface DateAfterToday {

    String message() default "Моля изберете ден след днескашния";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

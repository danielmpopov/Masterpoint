package com.dmp.masterpoint.areas.projects.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateAfterTodayValidator implements ConstraintValidator<DateAfterToday, LocalDate> {
   public void initialize(DateAfterToday constraint) {
   }

   public boolean isValid(LocalDate date, ConstraintValidatorContext context) {

      if (date == null) {
         return false;
      }

      LocalDate today = LocalDate.now();
      return date.isAfter(today);
   }
}

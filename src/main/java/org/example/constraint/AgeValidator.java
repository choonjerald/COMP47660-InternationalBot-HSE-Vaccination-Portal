package org.example.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class AgeValidator implements ConstraintValidator<CheckAge, String> {

    @Override
    public void initialize(CheckAge constraintAnnotation) {
    }

    @Override
    public boolean isValid(String sDate, ConstraintValidatorContext constraintValidatorContext) {
        if (sDate.equals("")) {
            return false;
        }

        try {
            Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
            LocalDate today = LocalDate.now();
            Period age = Period.between(birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), today);
            return age.getYears() >= 18;
        } catch (ParseException e) {
            return false;
        }

    }
}

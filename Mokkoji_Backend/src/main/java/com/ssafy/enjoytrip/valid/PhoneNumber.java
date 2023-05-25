package com.ssafy.enjoytrip.valid;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumber {

    String message() default "올바른 전화번호 형식이 아닙니다";

    Class[] groups() default {};

    Class[] payload() default {};

}

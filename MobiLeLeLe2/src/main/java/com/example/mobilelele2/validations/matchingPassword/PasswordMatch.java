package com.example.mobilelele2.validations.matchingPassword;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//създаване на АНОТАЦИЯ за сравнение на двете пароли при регистрация
@Retention(RetentionPolicy.RUNTIME)//да сравнява по време на вървенето на програмата
@Target(ElementType.TYPE)//казва че можем да я сложим върху самия клас
@Constraint(validatedBy = PasswordMatcher.class)
public @interface PasswordMatch {

    String password();
    String confirmPassword();
    String message() default "Passwords miss match";

    Class<?> [] groups() default {};//това е хубаво да го има
    Class<? extends Payload> [] payload() default {};
}

package com.example.mobilelele2.validations.matchingPassword;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class PasswordMatcher implements ConstraintValidator<PasswordMatch, Object> {
    //обекта който взимаме е нашето ентити
    private String password;
    private String confirmPassword;
    private String message;


    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.confirmPassword = constraintAnnotation.confirmPassword();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        //извлича моите полета от ентитито (регистрацията)
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
        //взема двете пароли
        Object passwordValue = beanWrapper.getPropertyValue(this.password);
        Object confirmPasswordValue = beanWrapper.getPropertyValue(this.confirmPassword);

        //ако паролата е различна от нула и двете пароли съвпадат
        if (passwordValue != null && passwordValue.equals(confirmPasswordValue)) {
            return true;
        }

        //да върна съобщение към втората парола че е грешна
        constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(confirmPassword)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();


        return false;
    }
}

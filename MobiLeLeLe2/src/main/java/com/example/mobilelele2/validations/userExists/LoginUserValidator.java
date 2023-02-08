package com.example.mobilelele2.validations.userExists;

import com.example.mobilelele2.domain.dtoS.banding.UserLoginFormDto;
import com.example.mobilelele2.domain.enitities.User;
import com.example.mobilelele2.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public record LoginUserValidator(UserRepository userRepository,
                                 ModelMapper modelMapper)
        implements ConstraintValidator<ValidateLoginUser, UserLoginFormDto> {//тук направо подаваме обекта върху който ще работим, по този начин няма нужда да пишем инициалайзъра

    @Override
    public void initialize(ValidateLoginUser constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginFormDto userLogin, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> loginCandidate = this.userRepository.findByUsername(userLogin.getUsername());

        return loginCandidate.isPresent()//дали потребителя съществува в базата
                && loginCandidate.get()
                .getPassword()//ще сравни паролата с тази от базата
                .equals(userLogin.getPassword());
    }
}

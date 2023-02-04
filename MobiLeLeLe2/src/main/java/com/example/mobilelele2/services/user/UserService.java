package com.example.mobilelele2.services.user;

import com.example.mobilelele2.domain.dtoS.banding.UserLoginFormDto;
import com.example.mobilelele2.domain.dtoS.banding.UserRegisterFormDto;
import com.example.mobilelele2.domain.dtoS.model.UserModel;

public interface UserService {

    //метод който ни казва дали трябва да регистрираме юзъра
    UserModel registerUser(UserRegisterFormDto userRegister);

    //метод който логва самия юзър
    UserModel loginUser(UserLoginFormDto userLogin);
}

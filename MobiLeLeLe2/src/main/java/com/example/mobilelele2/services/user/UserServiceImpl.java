package com.example.mobilelele2.services.user;

import com.example.mobilelele2.domain.dtoS.banding.UserRegisterFormDto;
import com.example.mobilelele2.domain.dtoS.model.UserModel;
import com.example.mobilelele2.domain.dtoS.model.UserRoleModel;
import com.example.mobilelele2.repositories.UserRepository;
import com.example.mobilelele2.services.init.DataBaseInitServiceService;
import com.example.mobilelele2.services.role.UserRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, DataBaseInitServiceService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void dbInit() {

    }

    @Override
    public boolean isDbInit() {
        return this.userRepository.count() > 0;
    }

    //метод който ни казва дали трябва да регистрираме юзъра
    public void registerUser(UserRegisterFormDto userRegister) {
        UserModel userModel = this.modelMapper.map(userRegister, UserModel.class);

        if (this.userRepository.count() > 0) {
            userModel.setRole(this.userRoleService.findAllRoles());
        }else {

        }

    }

    //метод който логва самия юзър
    public void loginUser() {

    }

}
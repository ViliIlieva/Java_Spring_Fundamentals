package com.example.mobilelele2.services.user;

import com.example.mobilelele2.domain.beans.LoggedUser;
import com.example.mobilelele2.domain.dtoS.banding.UserLoginFormDto;
import com.example.mobilelele2.domain.dtoS.banding.UserRegisterFormDto;
import com.example.mobilelele2.domain.dtoS.model.UserModel;
import com.example.mobilelele2.domain.enitities.User;
import com.example.mobilelele2.repositories.UserRepository;
import com.example.mobilelele2.services.init.DataBaseInitServiceService;
import com.example.mobilelele2.services.role.UserRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, DataBaseInitServiceService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    @Override
    public void dbInit() {

    }

    @Override
    public boolean isDbInit() {
        return this.userRepository.count () > 0;
    }


    @Override
    //метод който ни казва дали трябва да регистрираме юзъра
    public UserModel registerUser(UserRegisterFormDto userRegister) {//ако ти си първи ти слагам всички роли, такава е практиката а не юзъра сам да изпира
        final UserModel userModel = this.modelMapper.map (userRegister, UserModel.class);

        //направо сетваме ролята // ако няма юзъри т.е базата е празна сетни да има и двете роли
        //вземи всичките роли от базата
        // ако има юзъри върни ролята да е само юзър
        userModel.setRole (this.userRepository.count () == 0
                ? this.userRoleService.findAllRoles ()
                : List.of (this.userRoleService.findRoleByName ("USER")));

        final User userToSave = this.modelMapper.map (userModel, User.class);

        return this.modelMapper.map (this.userRepository.saveAndFlush (userToSave), UserModel.class);
    }

    @Override
    //метод който логва самия юзър
    public void loginUser(UserLoginFormDto userLogin) {

        UserModel loginCandidate = //вземам полето и с помоща на модел мапър го обръщам към юзър модела ми
                this.modelMapper.map(this.userRepository.findByUsername(userLogin.getUsername()).get(),
                        UserModel.class);

        this.loggedUser //заместваме информацията на логин кандидата
                .setId(loginCandidate.getId())
                .setUsername(loginCandidate.getUsername())
                .setRoleModels(loginCandidate.getRole());
    }

    @Override
    public void logout() {
        this.loggedUser.clearFields ();
    }

}
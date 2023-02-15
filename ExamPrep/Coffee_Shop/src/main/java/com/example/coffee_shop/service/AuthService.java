package com.example.coffee_shop.service;

import com.example.coffee_shop.models.dtos.bindingModels.LoginDTO;
import com.example.coffee_shop.models.dtos.bindingModels.UserRegistrationDTO;
import com.example.coffee_shop.models.entity.User;
import com.example.coffee_shop.repository.UserRepository;
import com.example.coffee_shop.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final LoggedUser userSession;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthService(UserRepository userRepository,
                       LoggedUser userSession,
                       ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.modelMapper = modelMapper;
    }

    public boolean isLoggedIn() {
        return this.userSession.getId() > 0;
    }

    public boolean register(UserRegistrationDTO registrationDTO) {
        //дали двете пароли съвпадат
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            return false;
        }

        User user = modelMapper.map (registrationDTO, User.class);
        this.userRepository.save(user);
        return true;
    }

    public boolean login(LoginDTO loginDTO) {
        //създай ми юзър
        //открий по юзър нейм и парола, подадени от формата дали ги има в репозиторито
        Optional<User> user = this.userRepository
                .findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (user.isEmpty()) {//ако не сме открили такъв юзър в базата
            return false;
        }
        //ако има такъв го логни, като сетваме ID-то му и цялото му име и върни тру
        this.userSession.login(user.get());

        return true;
    }

    public void logout() {
        this.userSession.logout();
    }
}

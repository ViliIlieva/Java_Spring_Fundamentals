package com.example.exam.service;

import com.example.exam.models.dtos.bindingModels.LoginDTO;
import com.example.exam.models.dtos.bindingModels.UserRegistrationDTO;
import com.example.exam.models.entity.User;
import com.example.exam.repository.UserRepository;
import com.example.exam.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final LoggedUser userSession;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public AuthService(LoggedUser userSession, ModelMapper modelMapper, UserRepository userRepository) {
        this.userSession = userSession;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
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

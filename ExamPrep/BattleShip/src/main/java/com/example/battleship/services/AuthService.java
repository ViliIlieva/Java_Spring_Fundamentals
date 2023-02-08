package com.example.battleship.services;

import com.example.battleship.domain.dtos.LoginDTO;
import com.example.battleship.domain.dtos.UserRegistrationDTO;
import com.example.battleship.domain.entities.User;
import com.example.battleship.repositories.UserRepository;
import com.example.battleship.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final LoggedUser userSession;

    public AuthService(UserRepository userRepository, LoggedUser userSession) {
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    public boolean register(UserRegistrationDTO registrationDTO) {
        //дали двете пароли съвпадат
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            return false;
        }
        //търсим този имейл дали вече го има в базата регистриран
        Optional<User> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());
        if (byEmail.isPresent()) {
            return false;
        }
        //проверяваме дали username вече не съществува в базата
        Optional<User> byUsername = this.userRepository.findByUsername(registrationDTO.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }

        //създавам новия юзър и му сетвам параметрите от формата в която се подават
        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setEmail(registrationDTO.getEmail());
        user.setFullName(registrationDTO.getFullName());
        user.setPassword(registrationDTO.getPassword());
        //сейфа готовото ентити и връща true
        this.userRepository.save(user);

        return true;
    }


    public boolean login( LoginDTO loginDTO) {
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

    public boolean isLoggedIn() {
        return this.userSession.getId() > 0;
    }

    public long getLoggedUserId() {
        return this.userSession.getId();
    }
}

package com.example.shoppinglist.service;

import com.example.shoppinglist.model.dtos.LoginDTO;
import com.example.shoppinglist.model.dtos.UserRegistrationDTO;
import com.example.shoppinglist.model.entity.Product;
import com.example.shoppinglist.model.entity.User;
import com.example.shoppinglist.repository.ProductRepository;
import com.example.shoppinglist.repository.UserRepository;
import com.example.shoppinglist.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final LoggedUser userSession;
    private final ProductRepository productRepository;
    private final ProductService productService;

    @Autowired
    public AuthService(UserRepository userRepository,
                       LoggedUser userSession,
                       ProductRepository productRepository, ProductService productService) {
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    public boolean isLoggedIn() {
        return this.userSession.getId() > 0;
    }

    public boolean register(UserRegistrationDTO registrationDTO) {
        //дали двете пароли съвпадат
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            return false;
        }
        //имейла дали вече го има в базата, защото трябва да е уникален
        Optional<User> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());
        if (byEmail.isPresent()) {
            return false;
        }
        //дали юзъра вече го има в базата, защото трябва да е уникален
        Optional<User> byUsername = this.userRepository.findByUsername(registrationDTO.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }

        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(registrationDTO.getPassword());

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

    public long getLoggedUserId() {
        return this.userSession.getId();
    }
}


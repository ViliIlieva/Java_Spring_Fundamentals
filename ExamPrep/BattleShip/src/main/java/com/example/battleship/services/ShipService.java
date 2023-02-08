package com.example.battleship.services;

import com.example.battleship.domain.dtos.CreateShipDTO;
import com.example.battleship.domain.dtos.ShipDTO;
import com.example.battleship.domain.entities.Category;
import com.example.battleship.domain.entities.Ship;
import com.example.battleship.domain.entities.ShipType;
import com.example.battleship.domain.entities.User;
import com.example.battleship.repositories.CategoryRepository;
import com.example.battleship.repositories.ShipRepository;
import com.example.battleship.repositories.UserRepository;
import com.example.battleship.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final CategoryRepository categoryRepository;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;

    public ShipService(ShipRepository shipRepository,
                       CategoryRepository categoryRepository,
                       LoggedUser loggedUser,
                       UserRepository userRepository) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    //съхрани кораб
    public boolean create(CreateShipDTO createShipDTO) {

        //търсим дали има такъв кораб по име в базата, защото името трябва да е уникално
        Optional<Ship> byName =
            this.shipRepository.findByName(createShipDTO.getName());

        //ако съществува в базата върни грешка
        if (byName.isPresent()) {
            return false;
        }

        //използваме switch за да зададем категория на кораба спрямо цифрата която са ни подали
        ShipType type = switch (createShipDTO.getCategory()) {
            case 0 -> ShipType.BATTLE;
            case 1 -> ShipType.CARGO;
            case 2 -> ShipType.PATROL;
            default -> ShipType.BATTLE;
        };

        //открий категорията по име което сме сетнали в горния суич
        Category category = this.categoryRepository.findByName(type);
        //открий собственика, по id на текущата сесия на логнатия юзър, който въвежда
        //ние сме сигурни че такъв юзър съществува щом сме проверили дали е логнат за да може да въвежда кораб
        Optional<User> owner = this.userRepository.findById(this.loggedUser.getId());

        //правим новия кораб, сетни му стойностите от подадените във формата
        Ship ship = new Ship();
        ship.setName(createShipDTO.getName());
        ship.setPower(createShipDTO.getPower());
        ship.setHealth(createShipDTO.getHealth());
        ship.setCreated(createShipDTO.getCreated());
        ship.setCategory(category);//подаваме откритата по горе категория
        ship.setUser(owner.get());

        //съхрани го в базата и върни тру
        this.shipRepository.save(ship);

        return true;
    }

    public List<ShipDTO> getShipsOwnedBy(long ownerId) {
        return this.shipRepository.findByUserId(ownerId)
            .stream()
            .map(ShipDTO::new)
            .collect(Collectors.toList());
    }


    public List<ShipDTO> getShipsNotOwnedBy(long ownerId) {
        return this.shipRepository.findByUserIdNot(ownerId)
            .stream()
            .map(ShipDTO::new)
            .collect(Collectors.toList());
    }


    public List<ShipDTO> getAllSorted() {
        return this.shipRepository.findByOrderByHealthAscNameDescPowerAsc()
            .stream()
            .map(ShipDTO::new)
            .collect(Collectors.toList());
    }
}

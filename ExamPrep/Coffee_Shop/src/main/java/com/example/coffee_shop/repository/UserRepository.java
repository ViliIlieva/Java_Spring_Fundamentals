package com.example.coffee_shop.repository;

import com.example.coffee_shop.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String value);

    Optional<User> findByEmail(String value);

    @Query("SELECT u FROM User u ORDER BY size(u.orders) DESC")
    List<User> findCountOfOrdersByEmployee();
}

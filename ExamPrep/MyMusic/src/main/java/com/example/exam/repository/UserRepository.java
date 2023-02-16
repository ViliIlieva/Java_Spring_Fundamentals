package com.example.exam.repository;

import com.example.exam.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT u FROM User u ORDER BY size(u.orders) DESC")
//    List<User> findCountOfOrdersByEmployee();

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String value);

    Optional<User> findByEmail(String value);
}

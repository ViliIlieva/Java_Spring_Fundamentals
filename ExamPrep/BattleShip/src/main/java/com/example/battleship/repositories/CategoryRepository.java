package com.example.battleship.repositories;


import com.example.battleship.domain.entities.Category;
import com.example.battleship.domain.entities.ShipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(ShipType name);
}

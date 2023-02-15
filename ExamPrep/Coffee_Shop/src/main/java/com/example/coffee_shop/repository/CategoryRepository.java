package com.example.coffee_shop.repository;

import com.example.coffee_shop.models.entity.Category;
import com.example.coffee_shop.models.entity.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(CategoryName name);
}

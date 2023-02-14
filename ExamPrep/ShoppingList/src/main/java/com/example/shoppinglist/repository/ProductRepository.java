package com.example.shoppinglist.repository;

import com.example.shoppinglist.model.dtos.ProductDTO;
import com.example.shoppinglist.model.entity.Category;
import com.example.shoppinglist.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Set<Product> findByCategory(Category category);

}

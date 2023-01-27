package com.example.mobilelele2.repositories;

import com.example.mobilelele2.domain.enitities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {
}


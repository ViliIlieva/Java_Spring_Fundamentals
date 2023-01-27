package com.example.mobilelele2.repositories;

import com.example.mobilelele2.domain.enitities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {
}

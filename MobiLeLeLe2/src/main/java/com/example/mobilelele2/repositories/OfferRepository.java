package com.example.mobilelele2.repositories;

import com.example.mobilelele2.domain.enitities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {
}

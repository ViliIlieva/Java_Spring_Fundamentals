package com.example.exam.repository;

import com.example.exam.models.entity.Offer;
import com.example.exam.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findOfferByCreator_Id(Long id);

    List<Offer> findOfferByCreator_IdNot(Long id);


}

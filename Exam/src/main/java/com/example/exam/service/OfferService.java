package com.example.exam.service;

import com.example.exam.models.dtos.bindingModels.AddOfferDTO;
import com.example.exam.models.entity.Offer;
import com.example.exam.models.entity.User;
import com.example.exam.repository.ConditionRepository;
import com.example.exam.repository.OfferRepository;
import com.example.exam.repository.UserRepository;
import com.example.exam.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;
    private final ConditionRepository conditionRepository;
    private final OfferRepository offerRepository;

    @Autowired
    public OfferService(UserRepository userRepository, LoggedUser loggedUser,
                        ModelMapper modelMapper, ConditionRepository conditionRepository,
                        OfferRepository offerRepository) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
        this.conditionRepository = conditionRepository;
        this.offerRepository = offerRepository;
    }

    public boolean addOffer(AddOfferDTO addOfferDTO) {
        Offer offer = modelMapper.map(addOfferDTO, Offer.class);
        User user = this.userRepository.findById (this.loggedUser.getId ()).get ();

        user.getOffers ().add (offer);

        offer.setCreator (userRepository.findByUsername (loggedUser.getUsername ()).get ());
        offer.setCondition (conditionRepository.findByConditionName (addOfferDTO.getCondition ()));
        this.offerRepository.save (offer);
        this.userRepository.save (user);
        return true;
    }

    public void removeOfferById(Long id) {
        this.offerRepository.deleteById (id);
    }
}

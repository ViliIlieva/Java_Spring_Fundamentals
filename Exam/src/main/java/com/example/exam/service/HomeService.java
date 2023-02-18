package com.example.exam.service;

import com.example.exam.models.dtos.viewModels.OfferDTO;
import com.example.exam.models.entity.Offer;
import com.example.exam.models.entity.User;
import com.example.exam.repository.OfferRepository;
import com.example.exam.repository.UserRepository;
import com.example.exam.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HomeService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;

    @Autowired
    public HomeService(OfferRepository offerRepository, ModelMapper modelMapper,
                       LoggedUser loggedUser, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    public List<OfferDTO> getMyOffer() {
        return this.offerRepository.findOfferByCreator_Id (this.loggedUser.getId ())
                .stream ()
                .map (offer -> modelMapper.map (offer, OfferDTO.class))
                .toList ();
    }

    public List<OfferDTO> boughtOffer() {
        User user = this.userRepository.findById (this.loggedUser.getId ()).get ();
        return user.getOffers ().stream ()
                .map (offer -> modelMapper.map (offer, OfferDTO.class))
                .toList ();
    }

    public List<OfferDTO> getOffersToOtherUsers() {
        List<User> users = this.userRepository.findByIdNot (this.loggedUser.getId ());
        Set<Offer> offers = new HashSet<> ();

        for (User user : users) {
            offers.add ((Offer) user.getOffers ());
        }

        return this.offerRepository.findOfferByCreator_IdNot (this.loggedUser.getId ())
                .stream ()
                .map (offer -> modelMapper.map (offer, OfferDTO.class))
                .toList ();
    }

    public void buyOfferWithId(Long offerId, long userId) {
        User buyer = this.userRepository.findById (userId).get ();
        Offer offer = this.offerRepository.findById (offerId).get ();
        User seller = offer.getCreator ();

        buyer.getBoughtOffers ().add (offer);
        seller.getOffers ().remove (offer);

        this.offerRepository.save (offer);
        this.userRepository.save (buyer);
        this.userRepository.save (seller);
    }
}

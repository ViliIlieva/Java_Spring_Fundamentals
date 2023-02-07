package com.example.mobilelele2.services.offers;

import com.example.mobilelele2.services.init.DataBaseInitServiceService;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService, DataBaseInitServiceService {
    @Override
    public void dbInit() {

    }

    @Override
    public boolean isDbInit() {
        return false;
    }
}
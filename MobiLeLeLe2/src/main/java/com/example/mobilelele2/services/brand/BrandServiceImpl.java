package com.example.mobilelele2.services.brand;

import com.example.mobilelele2.repositories.BrandRepository;
import com.example.mobilelele2.services.init.DataBaseInitServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService, DataBaseInitServiceService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void dbInit() {

    }

    @Override
    public boolean isDbInit() {
        return this.brandRepository.count() > 0;
    }
}
package bg.softuni.intro.cats.service;

import bg.softuni.intro.cats.model.DTO.CreateOwnerDTO;
import org.springframework.stereotype.Service;


public interface OwnerService {

    void createOwner(CreateOwnerDTO createOwnerDTO);
}

package com.example.exam.service;

import com.example.exam.models.dtos.bindingModels.AddAlbumDTO;
import com.example.exam.models.entity.Album;
import com.example.exam.repository.AlbumRepository;
import com.example.exam.repository.ArtistRepository;
import com.example.exam.repository.UserRepository;
import com.example.exam.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {
    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AlbumService(ArtistRepository artistRepository,
                        UserRepository userRepository,
                        LoggedUser loggedUser,
                        AlbumRepository albumRepository, ModelMapper modelMapper) {
        this.artistRepository = artistRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
    }

    public boolean addAlbum(AddAlbumDTO addAlbumDTO) {
        Album album = modelMapper.map (addAlbumDTO, Album.class);

        album.setAddedFrom (userRepository.findByUsername (loggedUser.getUsername ()).get ());
        album.setArtist (artistRepository.findByName(addAlbumDTO.getArtist ()));
        this.albumRepository.save (album);
        return true;
    }

    public void deleteAlbumById(Long id) {
        this.albumRepository.deleteById (id);
    }
}

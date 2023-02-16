package com.example.exam.service;

import com.example.exam.models.dtos.viewModels.AllAlbumDTO;
import com.example.exam.models.entity.Album;
import com.example.exam.repository.AlbumRepository;
import com.example.exam.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {
    private final ModelMapper modelMapper;
    private final AlbumRepository albumRepository;

    @Autowired
    public HomeService(ModelMapper modelMapper, AlbumRepository albumRepository) {
        this.modelMapper = modelMapper;
        this.albumRepository = albumRepository;
    }

    public List<AllAlbumDTO> getAllAlbums() {
        return this.albumRepository.findAllByOrderByCopiesDesc ()
                .stream ()
                .map (album -> {
                    AllAlbumDTO albums = modelMapper.map (album, AllAlbumDTO.class);
                    albums.setArtist (album.getArtist ());
                    return albums;
                })
                .toList ();
    }

    public int getAlbumsTotalSoldCopies(){
        return this.albumRepository.findAll ()
                .stream ()
                .map (Album::getCopies)
                .reduce (Integer::sum)
                .orElse (0);
    }
}

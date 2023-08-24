package com.exercice.chinook.service;

import com.exercice.chinook.dto.AlbumDTO;
import com.exercice.chinook.dto.ArtistDto;
import com.exercice.chinook.dto.TrackDto;
import com.exercice.chinook.model.AlbumEntity;
import com.exercice.chinook.model.ArtistEntity;
import com.exercice.chinook.model.NotFoundException;
import com.exercice.chinook.model.TrackEntity;
import com.exercice.chinook.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistAlbumService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistAlbumService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public ArtistDto getArtistAlbums(long artistId) {
        ArtistEntity artistEntity = artistRepository.findById(artistId)
                .orElseThrow(() -> new NotFoundException("Artiste non trouvé avec l'ID: " + artistId));

        List<AlbumDTO> albums = artistEntity.getAlbumEntities().stream().map(this::mapToAlbumDTO)
                .sorted((a1, a2) -> a1.getTitle().compareToIgnoreCase(a2.getTitle()))
                .collect(Collectors.toList());

        return new ArtistDto(
                artistEntity.getName(),
                albums
        );


    }

    private AlbumDTO mapToAlbumDTO(AlbumEntity albumEntity) {
        List<TrackEntity> trackEntities = albumEntity.getTrackEntities();
        List<TrackDto> tracks = trackEntities.stream()
                .map(trackEntity -> new TrackDto(
                        trackEntity.getName(),
                        trackEntity.getGenreEntity().getName(),
                        trackEntity.getMilliseconds()
                ))
                .collect(Collectors.toList());
        int totalDuration = trackEntities.stream()
                .mapToInt(TrackEntity::getMilliseconds)
                .sum();

        return new AlbumDTO(
                albumEntity.getTitle(),
                tracks,
                totalDuration
        );
    }
}

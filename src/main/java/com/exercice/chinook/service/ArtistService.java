package com.exercice.chinook.service;

import com.exercice.chinook.dto.ArtistStatsDTO;
import com.exercice.chinook.model.AlbumEntity;
import com.exercice.chinook.model.ArtistEntity;
import com.exercice.chinook.model.TrackEntity;
import com.exercice.chinook.repository.AlbumRepository;
import com.exercice.chinook.repository.ArtistRepository;
import com.exercice.chinook.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final TrackRepository trackRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, AlbumRepository albumRepository, TrackRepository trackRepository) {
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.trackRepository = trackRepository;
    }

    public List<ArtistStatsDTO> getArtistStats() {
        List<ArtistStatsDTO> artistStatsList = new ArrayList<>();

        List<ArtistEntity> artists = artistRepository.findAll();

        for (ArtistEntity artist : artists) {
            List<AlbumEntity> albums = albumRepository.findByArtistEntity(artist);

            int currentConsecutiveYears = 0;
            int maxConsecutiveYears = 0;
            List<Integer> yearsWith3MoreSales = new ArrayList<>();

            for (AlbumEntity album : albums) {
                List<TrackEntity> tracks = trackRepository.findByAlbumEntity(album);
                int totalSales = tracks.size();

                if (totalSales >= 3) {
                    currentConsecutiveYears++;

                    if (currentConsecutiveYears > 1) {
                        maxConsecutiveYears = currentConsecutiveYears;
                        yearsWith3MoreSales.add(album.getSalesYears());
                    } else  {
                        yearsWith3MoreSales.clear();
                        yearsWith3MoreSales.add(album.getSalesYears());
                    }
                } else {
                    currentConsecutiveYears = 0;
                }
            }

            if (maxConsecutiveYears > 0) {
                ArtistStatsDTO artistStatsDTO = new ArtistStatsDTO();
                artistStatsDTO.setArtistName(artist.getName());
                artistStatsDTO.setMaxConsecutiveYears(maxConsecutiveYears);
                artistStatsDTO.setYearsWith3MoreSales(yearsWith3MoreSales);
                artistStatsList.add(artistStatsDTO);
            }
        }

        return artistStatsList;
    }
}

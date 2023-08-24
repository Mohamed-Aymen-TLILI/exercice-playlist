package com.exercice.chinook;

import com.exercice.chinook.dto.ArtistStatsDTO;
import com.exercice.chinook.model.AlbumEntity;
import com.exercice.chinook.model.ArtistEntity;
import com.exercice.chinook.model.TrackEntity;
import com.exercice.chinook.repository.AlbumRepository;
import com.exercice.chinook.repository.ArtistRepository;
import com.exercice.chinook.repository.TrackRepository;
import com.exercice.chinook.service.ArtistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ArtistServiceTest {

    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private TrackRepository trackRepository;

    private ArtistService artistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        artistService = new ArtistService(artistRepository, albumRepository, trackRepository);
    }

    @Test
    void should_return_artist_stats_with_consecutive_years() {
        // Mock data
        ArtistEntity artist = new ArtistEntity();
        artist.setName("Artist1");

        AlbumEntity album1 = new AlbumEntity();
        album1.setSalesYears(2008);
        AlbumEntity album2 = new AlbumEntity();
        album2.setSalesYears(2009);
        AlbumEntity album3 = new AlbumEntity();
        album3.setSalesYears(2010);

        when(artistRepository.findAll()).thenReturn(List.of(artist));
        when(albumRepository.findByArtistEntity(artist)).thenReturn(List.of(album1, album2, album3));

        TrackEntity track1 = new TrackEntity();
        TrackEntity track2 = new TrackEntity();
        TrackEntity track3 = new TrackEntity();

        when(trackRepository.findByAlbumEntity(album1)).thenReturn(List.of(track1, track2, track3));
        when(trackRepository.findByAlbumEntity(album2)).thenReturn(List.of(track1, track2, track3));
        when(trackRepository.findByAlbumEntity(album3)).thenReturn(List.of(track1, track2, track3));

        // Test the service method
        List<ArtistStatsDTO> artistStatsList = artistService.getArtistStats();

        // Assertions
        assertEquals(1, artistStatsList.size());
        ArtistStatsDTO artistStatsDTO = artistStatsList.get(0);
        assertEquals("Artist1", artistStatsDTO.getArtistName());
        assertEquals(3, artistStatsDTO.getMaxConsecutiveYears());
        assertEquals(List.of(2008, 2009, 2010), artistStatsDTO.getYearsWith3MoreSales());

        // Verify interactions with mock repositories
        verify(artistRepository, times(1)).findAll();
        verify(albumRepository, times(1)).findByArtistEntity(artist);
        verify(trackRepository, times(3)).findByAlbumEntity(any(AlbumEntity.class));
    }

    @Test
    void should_return_0_when_call_artisteService_getArtistStats() {
        // Mock data
        ArtistEntity artist = new ArtistEntity();
        artist.setName("Artist1");

        AlbumEntity album1 = new AlbumEntity();
        album1.setSalesYears(2008);
        AlbumEntity album2 = new AlbumEntity();
        album2.setSalesYears(2009);
        AlbumEntity album3 = new AlbumEntity();
        album3.setSalesYears(2010);

        when(artistRepository.findAll()).thenReturn(List.of(artist));
        when(albumRepository.findByArtistEntity(artist)).thenReturn(List.of(album1, album2, album3));

        TrackEntity track1 = new TrackEntity();
        TrackEntity track2 = new TrackEntity();
        TrackEntity track3 = new TrackEntity();

        when(trackRepository.findByAlbumEntity(album1)).thenReturn(List.of(track1));
        when(trackRepository.findByAlbumEntity(album2)).thenReturn(List.of(track2, track3));
        when(trackRepository.findByAlbumEntity(album3)).thenReturn(List.of(track1, track2, track3));

        // Test the service method
        List<ArtistStatsDTO> artistStatsList = artistService.getArtistStats();

        // Assertions
        assertEquals(0, artistStatsList.size());
    }
}

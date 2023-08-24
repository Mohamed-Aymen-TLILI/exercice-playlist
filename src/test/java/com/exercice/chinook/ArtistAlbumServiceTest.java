package com.exercice.chinook;

import com.exercice.chinook.model.NotFoundException;
import com.exercice.chinook.repository.ArtistRepository;
import com.exercice.chinook.service.ArtistAlbumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArtistAlbumServiceTest {
    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private ArtistAlbumService artistAlbumService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetArtistAlbums_ArtistNotFound() {
        long artistId = 1L;

        when(artistRepository.findById(artistId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> artistAlbumService.getArtistAlbums(artistId));

        verify(artistRepository, times(1)).findById(artistId);
    }
}

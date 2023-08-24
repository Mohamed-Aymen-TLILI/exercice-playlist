package com.exercice.chinook.controller;

import com.exercice.chinook.dto.ArtistDto;
import com.exercice.chinook.dto.ArtistStatsDTO;
import com.exercice.chinook.service.ArtistAlbumService;
import com.exercice.chinook.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ws")
public class ArtistController {

    private final ArtistService artistService;

    private final ArtistAlbumService artistAlbumService;

    @Autowired
    public ArtistController(ArtistService artistService, ArtistAlbumService artistAlbumService) {
        this.artistService = artistService;
        this.artistAlbumService = artistAlbumService;
    }

    @GetMapping("/api/artistsales")
    public ResponseEntity<List<ArtistStatsDTO>> getArtistStats() {
        List<ArtistStatsDTO> artistStatsList = artistService.getArtistStats();
        return ResponseEntity.ok(artistStatsList);
    }

    @GetMapping("api/artistalbum/{artistId}")
    public ResponseEntity<ArtistDto> getArtistDetails(@PathVariable long artistId) {
        ArtistDto artistDetails = artistAlbumService.getArtistAlbums(artistId);
        return ResponseEntity.ok(artistDetails);
    }
}

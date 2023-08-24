package com.exercice.chinook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDto {
    private String name;
    private List<AlbumDTO> albums;
}

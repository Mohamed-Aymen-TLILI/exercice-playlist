package com.exercice.chinook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDTO {

    private String title;
    private List<TrackDto> tracks;
    private int totalDuration;
}

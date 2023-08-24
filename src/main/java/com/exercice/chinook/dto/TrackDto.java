package com.exercice.chinook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackDto {
    private String name;
    private String genre;
    private Integer duration;
}

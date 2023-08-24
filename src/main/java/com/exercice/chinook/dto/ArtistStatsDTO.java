package com.exercice.chinook.dto;

import lombok.Data;

import java.util.List;

@Data
public class ArtistStatsDTO {
    private String artistName;
    private int maxConsecutiveYears;
    private List<Integer> yearsWith3MoreSales;
}

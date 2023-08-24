package com.exercice.chinook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "genre", schema = "chinook")
public class GenreEntity extends BaseEntity {

    @Id
    @Column(name="genre_id")
    private long genreId;

    private String name;

    @OneToMany(fetch= FetchType.LAZY, cascade= CascadeType.ALL, mappedBy="genreEntity")
    private List<TrackEntity> trackEntities;

}

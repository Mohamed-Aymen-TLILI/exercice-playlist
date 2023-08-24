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
@Table(name = "artist", schema = "chinook")
public class ArtistEntity extends BaseEntity {

    @Id
    @Column(name="artist_id")
    private long artistId;

    private String name;

    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="artistEntity")
    private List<AlbumEntity> albumEntities;

}

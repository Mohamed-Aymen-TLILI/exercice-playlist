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
@Table(name = "album", schema = "chinook")
public class AlbumEntity extends BaseEntity {

    @Id
    @Column(name="album_id")
    private long albumId;

    private String title;

    private Integer salesYears;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="artist_id")
    private ArtistEntity artistEntity;

    @OneToMany(fetch= FetchType.LAZY, cascade= CascadeType.ALL, mappedBy="albumEntity")
    private List<TrackEntity> trackEntities;

}
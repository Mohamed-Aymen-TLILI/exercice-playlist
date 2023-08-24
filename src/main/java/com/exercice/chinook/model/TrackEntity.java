package com.exercice.chinook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "track", schema = "chinook")
public class TrackEntity extends BaseEntity {

    @Id
    @Column(name = "track_id")
    private long trackId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private AlbumEntity albumEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_type_id")
    private MediaTypeEntity mediaTypeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private GenreEntity genreEntity;

    private String name;
    private String composer;
    private Integer milliseconds;
    private Integer bytes;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

}

package com.exercice.chinook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "playlist_track", schema = "chinook")
public class PlaylistTrackEntity extends BaseEntity {

    @Id
    @Column(name="playlist_track_id")
    private long playlistTrackId;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="playlist_id")
    private PlaylistEntity playlistEntity;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="track_id")
    private TrackEntity trackEntity;

}

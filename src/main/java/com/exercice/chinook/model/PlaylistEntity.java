package com.exercice.chinook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "playlist", schema = "chinook")
public class PlaylistEntity extends BaseEntity {

    @Id
    @Column(name="playlist_id")
    private long playlistId;

    private String  name;
}

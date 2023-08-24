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
@Table(name = "media_type", schema = "chinook")
public class MediaTypeEntity extends BaseEntity {

    @Id
    @Column(name="media_type_id")
    private long mediaTypeId;

    private String  name;

    @OneToMany(fetch= FetchType.LAZY, cascade= CascadeType.ALL, mappedBy="mediaTypeEntity")
    private List<TrackEntity> trackEntities;

}

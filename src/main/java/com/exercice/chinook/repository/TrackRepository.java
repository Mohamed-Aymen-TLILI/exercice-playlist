package com.exercice.chinook.repository;

import com.exercice.chinook.model.AlbumEntity;
import com.exercice.chinook.model.TrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<TrackEntity, Long> {
    List<TrackEntity> findByAlbumEntity(AlbumEntity albumEntity);
}

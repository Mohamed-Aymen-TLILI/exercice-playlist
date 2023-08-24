package com.exercice.chinook.repository;

import com.exercice.chinook.model.AlbumEntity;
import com.exercice.chinook.model.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {
    List<AlbumEntity> findByArtistEntity(ArtistEntity artistEntity);
}

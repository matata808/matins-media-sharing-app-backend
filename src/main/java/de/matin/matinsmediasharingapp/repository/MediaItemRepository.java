package de.matin.matinsmediasharingapp.repository;

import de.matin.matinsmediasharingapp.domain.entity.MediaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MediaItemRepository extends JpaRepository<MediaItem, UUID> {

}

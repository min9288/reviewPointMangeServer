package com.reviewPointMangeServer.repository;

import com.reviewPointMangeServer.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlaceRepository extends JpaRepository<Place, UUID> {
    Optional<Place> findByPlaceName(String placeName);

    Optional<Place> findByPlaceId(UUID placeId);


}

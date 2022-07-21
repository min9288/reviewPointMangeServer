package com.reviewPointMangeServer.reviewPointMangeServer.photo.repository;

import com.reviewPointMangeServer.reviewPointMangeServer.photo.model.Attphoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Attphoto, Long> {
}

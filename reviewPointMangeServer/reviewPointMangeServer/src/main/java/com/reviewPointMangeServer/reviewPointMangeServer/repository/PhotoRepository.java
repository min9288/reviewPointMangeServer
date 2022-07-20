package com.reviewPointMangeServer.reviewPointMangeServer.repository;

import com.reviewPointMangeServer.reviewPointMangeServer.model.Attphoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Attphoto, Long> {
}

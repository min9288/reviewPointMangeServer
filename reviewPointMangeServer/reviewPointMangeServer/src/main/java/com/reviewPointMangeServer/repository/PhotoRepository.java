package com.reviewPointMangeServer.repository;

import com.reviewPointMangeServer.entity.Attphoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Attphoto, Long> {
}

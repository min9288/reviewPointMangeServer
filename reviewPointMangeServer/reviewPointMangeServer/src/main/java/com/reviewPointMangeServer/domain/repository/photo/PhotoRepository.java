package com.reviewPointMangeServer.domain.repository.photo;

import com.reviewPointMangeServer.domain.entity.photo.Attphoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Attphoto, Long> {
}

package com.tripleCMS.tripleCMS.repository;

import com.tripleCMS.tripleCMS.model.Attphoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PhotoRepository extends JpaRepository<Attphoto, Long> {
}

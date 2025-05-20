package com.filmNest.example.filmNest.repository;

import com.filmNest.example.filmNest.model.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashTagRepository extends JpaRepository<HashTag,Long> {
    Optional<HashTag> findByName(String tag);
}

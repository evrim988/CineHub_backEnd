package org.example.cinehub_backend.repository;

import org.example.cinehub_backend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}

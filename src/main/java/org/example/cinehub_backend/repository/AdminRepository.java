package org.example.cinehub_backend.repository;

import org.example.cinehub_backend.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

    Admin findByUsername(String username);
    Optional<Admin> findByEmail(String email);
}



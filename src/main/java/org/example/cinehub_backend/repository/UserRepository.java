package org.example.cinehub_backend.repository;

import org.example.cinehub_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
}

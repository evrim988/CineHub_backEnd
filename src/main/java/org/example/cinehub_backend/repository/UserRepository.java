package org.example.cinehub_backend.repository;

import org.example.cinehub_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUsernameAndPassword(String username, String password);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}

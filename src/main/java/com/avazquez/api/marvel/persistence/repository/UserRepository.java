package com.avazquez.api.marvel.persistence.repository;

import com.avazquez.api.marvel.persistence.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for user entities.
 *
 * <p>Provides CRUD operations and custom queries for {@link User} entities.
 */
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
}

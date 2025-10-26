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
  /**
   * Finds a user by their username.
   *
   * @param username the username to search for
   * @return an Optional containing the User if found, or empty if not found
   */
  Optional<User> findByUsername(String username);
}

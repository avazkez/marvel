package com.avazquez.api.marvel.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing a system permission.
 *
 * <p>Used for role-based access control.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Entity
@Getter
@Setter
public class Permission {
  /** Unique identifier for the permission. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** Name of the permission. */
  @Column(unique = true, nullable = false)
  private String name;
}

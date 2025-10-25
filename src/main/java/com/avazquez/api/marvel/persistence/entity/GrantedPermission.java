package com.avazquez.api.marvel.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

/**
 * Entity representing a permission granted to a role.
 *
 * <p>Links a {@link Role} to a {@link Permission}.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Entity
@Getter
public class GrantedPermission {
  /** Unique identifier for the granted permission. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** The role to which this permission is granted. */
  @ManyToOne
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;

  /** The permission granted to the role. */
  @ManyToOne
  @JoinColumn(name = "permission_id", nullable = false)
  private Permission permission;
}

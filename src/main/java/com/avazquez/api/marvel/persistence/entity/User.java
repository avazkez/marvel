package com.avazquez.api.marvel.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Entity representing an application user.
 *
 * <p>Implements Spring Security's {@link UserDetails} for authentication and authorization.
 * Contains username, password, role, and account status fields.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Entity
@Getter
@Setter
public class User implements UserDetails {
  /** Unique identifier for the user. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** Username for authentication (must be unique). */
  @Column(unique = true, nullable = false)
  private String username;

  /** Encrypted password for authentication. */
  private String password;

  /** Role assigned to the user. */
  @ManyToOne
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;

  /** Indicates if the account is expired. */
  private boolean accountExpired;

  /** Indicates if the account is locked. */
  private boolean accountLocked;

  /** Indicates if the user's credentials are expired. */
  private boolean credentialsExpired;

  /** Indicates if the user is enabled. */
  private boolean enabled;

  /**
   * Indicates whether the user's account is not expired.
   *
   * @return true if account is not expired
   */
  @Override
  public boolean isAccountNonExpired() {
    return !accountExpired;
  }

  /**
   * Indicates whether the user's account is not locked.
   *
   * @return true if account is not locked
   */
  @Override
  public boolean isAccountNonLocked() {
    return !accountLocked;
  }

  /**
   * Indicates whether the user's credentials are not expired.
   *
   * @return true if credentials are not expired
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return !credentialsExpired;
  }

  /**
   * Indicates whether the user is enabled.
   *
   * @return true if enabled
   */
  @Override
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * Returns the authorities granted to the user, including role and permissions.
   *
   * @return collection of granted authorities
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (role == null) return new ArrayList<>();
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
    if (role.getGrantedPermissions() == null) return authorities;
    role.getGrantedPermissions().stream()
        .forEach(
            grantedPermission -> {
              String permissionName = grantedPermission.getPermission().getName();
              authorities.add(new SimpleGrantedAuthority(permissionName));
            });
    return authorities;
  }
}

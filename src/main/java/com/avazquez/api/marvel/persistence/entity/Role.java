package com.avazquez.api.marvel.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * Entity representing a user role in the system.
 *
 * <p>Implements Spring Security's {@link GrantedAuthority} for role-based access control. Contains
 * a list of granted permissions and an enum for role type.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Entity
@Getter
@Setter
public class Role implements GrantedAuthority {
  /** Unique identifier for the role. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** Name of the role (enum). */
  @Enumerated(EnumType.STRING)
  private RoleEnum name;

  /** List of permissions granted to this role. */
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
  private List<GrantedPermission> grantedPermissions;

  /**
   * Returns the authority string for Spring Security.
   *
   * @return the role authority in the format ROLE_{NAME}
   */
  @Override
  public String getAuthority() {
    if (name == null) {
      return null;
    }
    return "ROLE_".concat(name.name());
  }

  /** Enum for supported role types. */
  public static enum RoleEnum {
    CUSTOMER,
    AUDITOR
  }
}

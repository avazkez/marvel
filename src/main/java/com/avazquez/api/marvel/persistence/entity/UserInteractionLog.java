package com.avazquez.api.marvel.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity for logging user interactions with the API.
 *
 * <p>Stores request details, user info, and timestamp for auditing and analytics.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
@Entity
@Getter
@Setter
public class UserInteractionLog {
  /** Unique identifier for the log entry. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** Requested URL (up to 600 characters). */
  @Column(length = 600)
  private String url;

  /** HTTP method used for the request. */
  private String httpMethod;

  /** Username of the user making the request. */
  private String username;

  /** Timestamp of the interaction. */
  private LocalDateTime timestamp;

  /** Remote IP address of the user. */
  private String remoteAddress;
}

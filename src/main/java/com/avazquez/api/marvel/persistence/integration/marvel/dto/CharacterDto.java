package com.avazquez.api.marvel.persistence.integration.marvel.dto;

/**
 * Data Transfer Object representing a Marvel character.
 *
 * <p>This record contains the essential information about a Marvel character as retrieved from the
 * Marvel API or database. It provides immutable access to character data including identification,
 * descriptive information, and resource links.
 *
 * @param id The unique identifier of the character
 * @param name The name of the character (e.g., "Spider-Man", "Iron Man")
 * @param description A brief description or biography of the character
 * @param modified The date when the character information was last modified
 * @param resourceURI The URI pointing to the full character resource in the Marvel API
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
public record CharacterDto(
    Long id, String name, String description, String modified, String resourceURI) {

  /**
   * Data Transfer Object representing detailed character information.
   *
   * <p>This nested record provides additional character details that are typically used for
   * detailed character views or specific character information requests.
   *
   * @param imagePath The path or URL to the character's image
   * @param description Detailed description or additional information about the character
   * @author Alex Vazquez
   * @version 1.0
   * @since 1.0
   */
  public static record CharacterInfoDto(String imagePath, String description) {}
}

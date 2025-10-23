package com.avazquez.api.marvel.persistence.integration.marvel.dto;

/**
 * Data Transfer Object representing a Marvel comic.
 *
 * <p>This record contains the essential information about a Marvel comic as retrieved from the
 * Marvel API or database. It provides immutable access to comic data including identification,
 * descriptive information, metadata, and associated thumbnail image.
 *
 * @param id The unique identifier of the comic
 * @param title The title of the comic (e.g., "Amazing Spider-Man #1", "Iron Man Vol 1 #1")
 * @param description A brief description or synopsis of the comic
 * @param modified The date when the comic information was last modified (ISO 8601 format)
 * @param resourceUri The URI pointing to the full comic resource in the Marvel API
 * @param thumbnail The thumbnail image information for the comic cover
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
public record ComicDto(
    Long id,
    String title,
    String description,
    String modified,
    String resourceUri,
    ThumbnailDto thumbnail) {}

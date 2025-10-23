package com.avazquez.api.marvel.persistence.integration.marvel.dto;

/**
 * Data Transfer Object representing thumbnail image information.
 *
 * <p>This record contains image metadata used for displaying comic and character thumbnails. The
 * path and extension can be combined to construct the full image URL for display purposes.
 *
 * @param path The base path to the thumbnail image (without file extension)
 * @param extension The file extension of the image (e.g., "jpg", "png", "gif")
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
public record ThumbnailDto(String path, String extension) {}

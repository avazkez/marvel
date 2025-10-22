package com.avazquez.api.marvel.dto;

/**
 * Data Transfer Object representing pagination parameters.
 *
 * <p>This record encapsulates pagination information used for limiting and offsetting query
 * results. It provides immutable access to pagination parameters commonly used in REST APIs.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * MyPageable pageable = new MyPageable(0, 20); // First page, 20 items
 * MyPageable secondPage = new MyPageable(20, 20); // Second page, 20 items
 * }</pre>
 *
 * @param offset The number of results to skip (0-based)
 * @param limit The maximum number of results to return (must be positive)
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
public record MyPageable(long offset, long limit) {}

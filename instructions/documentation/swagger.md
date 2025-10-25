# Swagger Documentation Instructions

## General Guidelines

- Use Swagger/OpenAPI annotations to document all REST controllers and endpoints.
- Annotate each controller class with `@Tag` to describe the API group.
- Annotate each endpoint method with `@Operation` for summary and description.
- Use `@ApiResponses` and `@ApiResponse` to document possible HTTP responses.
- Use `@Parameter` for each request parameter, describing its purpose and constraints.
- Use `@Schema` to describe complex request/response models if needed.

## Example

```java
@RestController
@RequestMapping("/api/characters")
@Tag(name = "Characters", description = "Marvel Characters API")
public class CharacterController {
    @Operation(summary = "Get all characters", description = "Retrieve a paginated list of Marvel characters.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = CharacterDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<CharacterDto>> findAll(...) {
        // ...
    }
}
```

## Best Practices

- Always provide meaningful summaries and descriptions for endpoints.
- Document all possible response codes, including errors.
- Keep annotations up to date with code changes.
- Use model schemas for complex objects to improve API clarity.

## Resources

- [Swagger/OpenAPI Specification](https://swagger.io/specification/)
- [Springdoc OpenAPI Documentation](https://springdoc.org/)
- [Swagger Annotations Reference](https://github.com/swagger-api/swagger-core/wiki/Annotations)

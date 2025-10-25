
# Javadoc Instructions

## General Guidelines

- Use Javadoc for all public classes, interfaces, methods, and fields.
- Start with a summary sentence describing the purpose of the element.
- Use `<p>` for paragraphs and `<ul>`/`<li>` for lists.
- Include `@param`, `@return`, and `@throws` tags for methods.
- Use `@author`, `@version`, and `@since` for classes and interfaces.

## Example

```java
/**
 * Service for managing Marvel characters.
 * <p>Provides methods for searching and retrieving character data.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 */
public interface CharacterService {
        /**
         * Finds characters by criteria.
         *
         * @param criteria Search criteria
         * @return List of matching characters
         */
        List<CharacterDto> findAll(CharacterSearchCriteria criteria);
}
```

## Best Practices

- Keep comments concise and relevant.
- Document exceptions and edge cases.
- Update Javadoc when method signatures or behavior change.

### Service Layer Documentation

- **Service Interfaces:**
  - Document the class and all public methods with full Javadoc.
- **Service Implementations:**
  - Document the class with Javadoc.
    - For methods, do not write custom Javadoc; use `/** {@inheritDoc} */` to inherit documentation from the interface.

- [Spring Documentation Standards](https://docs.spring.io/spring-framework/reference/core/beans/context-introduction.html#context-introduction-documentation)

# Testing Instructions

## General Guidelines

- Write unit tests for all business logic.
- Use integration tests for service and repository layers.
- Mock external dependencies in unit tests.

## API Testing Best Practices

- **Integration Tests:**
  - Test interactions between components (e.g., controllers, services, repositories).
  - Use real or in-memory databases (like H2) to verify persistence and business logic.
  - Mock external APIs/services if needed.
  - Run integration tests automatically in CI pipelines.

- **End-to-End (E2E) Tests:**
  - Test the entire application flow, simulating real user scenarios.
  - Use tools like Selenium, Cypress, or REST-assured for API endpoints.
  - E2E tests should cover authentication, authorization, error handling, and edge cases.
  - Run E2E tests in environments that closely resemble production.

- **General API Testing Best Practices:**
  - Automate all tests and run them on every commit/PR.
  - Isolate unit, integration, and E2E tests in separate suites.
  - Use clear naming and structure for test files.
  - Clean up test data after each run to avoid side effects.
  - Document test scenarios and expected outcomes.

## Best Practices

- Aim for high test coverage (90%+).
- Name test methods clearly to describe behavior.
- Use assertions to validate expected outcomes.
- Run tests before every commit and pull request.

## Mocking Guidelines

- Use mocking frameworks (e.g., Mockito) to simulate external dependencies in unit tests.
- Mock services, repositories, and external APIs to isolate the code under test.
- Avoid mocking internal logic; only mock dependencies.
- Use `@Mock` and `@InjectMocks` annotations for cleaner test setup.
- Verify interactions with mocks using `verify()`.

### Example (Mockito)

```java
@ExtendWith(MockitoExtension.class)
class CharacterServiceTest {
    @Mock
    private CharacterRepository characterRepository;

    @InjectMocks
    private CharacterServiceImpl characterService;

    @Test
    void testFindAllCharacters() {
        when(characterRepository.findAll()).thenReturn(List.of(new CharacterDto("Spider-Man")));
        List<CharacterDto> result = characterService.findAll();
        assertEquals(1, result.size());
        verify(characterRepository).findAll();
    }
}
```

## Cucumber Guidelines

- Use Cucumber for behavior-driven development (BDD), integration, and acceptance tests.
- Integrate Cucumber with JUnit for test execution.
- Cucumber works seamlessly with Spring Boot for context loading and dependency injection.
- You can use Mockito to mock dependencies in Cucumber step definitions.
- Write feature files in Gherkin syntax to describe scenarios and expected outcomes.
- Keep step definitions clean and focused on business logic.

### Example (Cucumber + Mockito)

```java
@SpringBootTest
@ContextConfiguration
public class CharacterSteps {
    @Mock
    private CharacterRepository characterRepository;

    @Autowired
    private CharacterService characterService;

    @Given("a character exists")
    public void aCharacterExists() {
        when(characterRepository.findAll()).thenReturn(List.of(new CharacterDto("Spider-Man")));
    }

    @When("I search for characters")
    public void iSearchForCharacters() {
        List<CharacterDto> result = characterService.findAll();
        assertEquals(1, result.size());
    }
}
```

## Resources

- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Spring Boot Testing](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [Cucumber Documentation](https://cucumber.io/docs/guides/10-minute-tutorial/)

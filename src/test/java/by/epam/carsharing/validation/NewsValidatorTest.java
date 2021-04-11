package by.epam.carsharing.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NewsValidatorTest {

    private static final NewsValidator VALIDATOR = new NewsValidator();

    @ParameterizedTest
    @ValueSource(strings = {"Новый автомобиль", "New car"})
    void testIsValidHeaderShouldReturnTrue(String header) {
        assertTrue(VALIDATOR.isValidHeader(header));
    }

    @ParameterizedTest
    @ValueSource(strings = {"с маленькой буквы", ""})
    void testIsValidHeaderShouldReturnFalse(String header) {
        assertFalse(VALIDATOR.isValidHeader(header));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Новый автомобиль", "New car"})
    void testIsValidContentShouldReturnTrue(String content) {
        assertTrue(VALIDATOR.isValidContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"с маленькой буквы", ""})
    void testIsValidContentShouldReturnFalse(String content) {
        assertFalse(VALIDATOR.isValidContent(content));
    }
}
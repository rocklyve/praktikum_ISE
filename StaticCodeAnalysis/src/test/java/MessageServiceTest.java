import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageServiceTest {
    static final String message = "Hello JUnit 5";

    @DisplayName("Test MessageService.get()")
    @Test
    void testGet() {
        ///
        assertEquals("Hello JUnit 5", message);
    }
}
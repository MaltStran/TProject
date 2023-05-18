package IntegrationTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class DataBaseTest extends IntegrationEnvironment {

    @Test
    public void testIfContainerIsRunning() {
        Assertions.assertTrue(CONTAINER.isRunning());
    }
}

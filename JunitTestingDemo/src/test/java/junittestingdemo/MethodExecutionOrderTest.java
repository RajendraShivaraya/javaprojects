package junittestingdemo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

//@TestMethodOrder(MethodOrderer.Random.class) // Execution method by random
//@TestMethodOrder(MethodOrderer.DisplayName.class) // Execution order by method names
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Execution order by method names
//@TestMethodOrder(MethodOrderer.MethodName.class) // Execution order by method names
public class MethodExecutionOrderTest
{
    @Order(4)
    @Test
    void testE() {
        assertTrue(true);
    }

    @Order(3)
    @Test
    void testA() {
        assertTrue(true);
    }

    @Order(1)
    @Test
    void testD() {
        assertTrue(true);
    }

    @Order(2)
    @Test
    void testC() {
        assertTrue(true);
    }

    @Order(5)
    @Test
    void testB() {
        assertTrue(true);
    }
}

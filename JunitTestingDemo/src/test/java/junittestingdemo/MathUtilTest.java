package junittestingdemo;

import org.junit.jupiter.api.*;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Math utility test")
@Tag("MathUtil")
class MathUtilTest // Class JunitMethodsTest instance is created for every method by default
{

    MathUtil mathUtil;

    @BeforeAll // Execute this method each time before other methods are executed.
    static void beforeAll()
    {
        System.out.println("...before all...");
    }

    @BeforeEach // Execute this method each time before other methods are executed.
    void init()
    {
        mathUtil = new MathUtil();
    }

    @AfterEach // Execute this method each time before other methods are executed.
    void close()
    {
        System.out.println("....closing....");
    }

    @Test
    @DisplayName("Add two numbers")
    void add()
    {
        assertEquals(4, mathUtil.add(2, 2), "This method should add two numbers");
    }

    @Test
    @DisplayName("Subtract number")
    void subtract() {
        assertEquals(0, mathUtil.subtract(2, 2), "This method subtract two numbers");
    }

    @Test
    @DisplayName("Multiply two numbers")
    void multiply() {
        assertEquals(4, mathUtil.multiply(2, 2), "This method should multiply two numbers");
    }

    @Test
    @DisplayName("Divide a numbers")
    void divide() {
        assertEquals(1, mathUtil.divide(2, 2), "Divide Function");
    }

    @Test
    @DisplayName("Divide a numbers exception")
    void divideTest()
    {
        assertAll(
                () -> assertThrows(ArithmeticException.class, () -> mathUtil.divide(1, 0), "Divide Function Should throw arithmetic exception"),
                () -> assertDoesNotThrow(() -> mathUtil.divide(1, 1), "Divide Function Should not throw exception")
        );
    }

    @Test
    @DisplayName("Loop Time")
    void loopTest()
    {
        assertTimeout(
                Duration.ofMillis(2),
                () -> { mathUtil.loopTest(100000); }
                );
    }
}
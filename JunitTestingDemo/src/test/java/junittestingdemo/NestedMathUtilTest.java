package junittestingdemo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Math utility nested test")
@Tag("NestedMathUtil")
public class NestedMathUtilTest
{
    MathUtil mathUtil;

    @BeforeAll
    void beforeAll()
    {
        mathUtil = new MathUtil();
    }

    @Nested
    @DisplayName("Add functionality test")
    @Tag("Add")
    class AddTestCases
    {
        @Test
        @DisplayName("Add positive numbers")
        void addPositiveNumbers()
        {
            assertEquals(4, mathUtil.add(2, 2), "This method should add two numbers");
        }
        @Test
        @DisplayName("Add negative numbers")
        void addNegativeNumbers()
        {
            assertEquals(-4, mathUtil.add(-2, -2), "This method should add two numbers");
        }
    }

    @Nested
    @DisplayName("Subtract functionality test")
    @Tag("Subtract")
    class SubtractTestCases
    {
        @Test
        @DisplayName("Subtract positive number")
        void subtractPositiveNumbers()
        {
            assertEquals(0, mathUtil.subtract(2, 2), "This method should subtract numbers");
        }
        @Test
        @DisplayName("Subtract negative number")
        void subtractNegativeNumbers()
        {
            assertEquals(4, mathUtil.subtract(2, -2), "This method should subtract numbers");
        }
    }

    @Nested
    @DisplayName("Multiply functionality test")
    @Tag("Multiply")
    class MultiplyTestCases
    {
        @Test
        @DisplayName("Multiply positive number")
        void multiplyPositiveNumbers()
        {
            assertEquals(4, mathUtil.multiply(2, 2), "This method should Multiply two numbers");
        }
        @Test
        @DisplayName("Multiply negative number")
        void multiplyNegativeNumbers()
        {
            assertEquals(4, mathUtil.multiply(-2, -2), "This method should Multiply two numbers");
        }
    }

    @Nested
    @DisplayName("Divide functionality test")
    @Tag("Divide")
    class DivideTestCases
    {
        @Test
        @DisplayName("Divide positive number")
        void DividePositiveNumbers()
        {
            assertEquals(1, mathUtil.divide(2, 2), "This method should Divide two numbers");
        }
        @Test
        @DisplayName("Divide negative number")
        void DivideNegativeNumbers()
        {
            assertEquals(1, mathUtil.divide(-2, -2), "This method should Divide two numbers");
        }
    }
}

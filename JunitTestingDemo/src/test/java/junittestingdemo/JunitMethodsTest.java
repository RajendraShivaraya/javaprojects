package junittestingdemo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Class JunitMethodsTest instance is just created once
@DisplayName("Test cases for standard methods")
@Tag("Standard")
public class JunitMethodsTest
{
    MathUtil mathUtil;

    @BeforeAll // Execute this method each time before other methods are executed.
    void beforeAll()
    {
        System.out.println("...before all...");
    }

    @BeforeEach // Execute this method each time before other methods are executed.
    void init()
    {
        // Init variables or anything else
        System.out.println("....before each....");
    }

    @AfterEach// Execute this method each time before other methods are executed.
    void close()
    {
        System.out.println("....closing....");
    }

    @Test
    @Disabled // Disable the any test case which you want to be executed
    void failTest()
    {
        fail("This is just make test fail");
    }

    @Test
    void testMethod()
    {
        System.out.println("Test method for Junit");
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void testLinuxMethod()
    {
        System.out.println("Method enabled only for Linux");
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testWindowsMethod()
    {
        System.out.println("Method enabled only for Windows");
    }

    @Test
    void assumeMethod()
    {
        boolean externalServerIsUp = false;
        assumeTrue(externalServerIsUp); // Next statements will execute only when variable is true else test case is broken.
        System.out.println("Server is up and running");
    }

    @RepeatedTest(4)
    void repeatedMethod(RepetitionInfo repetitionInfo)
    {
        System.out.println("Repetition cycle " + repetitionInfo.getCurrentRepetition()+ " of total "+ repetitionInfo.getTotalRepetitions());
    }
}

package junittestingdemo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@TestClassOrder(ClassOrderer.ClassName.class) // Execution order by Class Names
//@TestClassOrder(ClassOrderer.OrderAnnotation.class) // Execution order by order numbers
//@TestClassOrder(ClassOrderer.DisplayName.class) // Execution order by display name of classes
//@TestClassOrder(ClassOrderer.Random.class) // Execution of classes in random numbers
//@TestMethodOrder(MethodOrderer.Random.class) // Execution method by random
//@TestMethodOrder(MethodOrderer.DisplayName.class) // Execution order by method names
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Execution order by method names
//@TestMethodOrder(MethodOrderer.MethodName.class) // Execution order by method names
public class ClassExecutionOrderTest
{

    @Nested
    @Order(2)
    @DisplayName("PO test")
    class PurchaseOrder
    {
        @Test
        @DisplayName("D")
        @Order(4)
        void Atest3()        {        }
        @Test
        @Order(3)
        @DisplayName("A")
        void Ctest1()        {        }
        @Test
        @Order(1)
        @DisplayName("C")
        void Btest9()        {        }
        @Test
        @Order(2)
        @DisplayName("B")
        void Ztest6()        {        }
    }

    @Nested
    @Order(1)
    @DisplayName("SO test")
    class SalesOrder
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

    @Nested
    @Order(3)
    @DisplayName("TO test")
    class TransferOrder
    {
        @Test
        void test3()        {        }
        @Test
        void test1()        {        }
        @Test
        void test10()        {        }
        @Test
        void test6()        {        }
    }
}

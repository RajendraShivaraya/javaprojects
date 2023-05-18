package junittestingdemo;

import com.sun.tools.javac.Main;

import java.util.Arrays;
import java.util.List;

public class MathUtil
{
    public float add(float a, float b)
    {
        return a + b;
    }

    public float subtract(float a, float b)
    {
        return a - b;
    }

    public float multiply(float a, float b)
    {
        return a * b;
    }

    public float divide(float a, float b)
    {
        if (b == 0) {
            throw new ArithmeticException("Divide by zero");
        }
        return a / b;
    }

    public void loopTest(int n)
    {
        for (int i = 0; i <= n ; i++)
        {
            // do nothing, just loop
        }
    }

    public void lamdaTest()
    {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Filter even numbers and print their squares
        numbers.stream()
                .filter(n -> n % 2 == 0) // Filter 2 & 4
                .map(n -> n * n) // Produces n^2 of values i.e. 4 & 16
                .forEach(n -> printNumber(n)); // Print, perform action on each
    }

    public void printNumber(int n)
    {
        System.out.println(n);
    }

    public static void main(String[] args) {
        MathUtil mathUtil = new MathUtil();
        mathUtil.lamdaTest();
    }
}

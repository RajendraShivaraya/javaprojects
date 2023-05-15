package junittestingdemo;

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
}

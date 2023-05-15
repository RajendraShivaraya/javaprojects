package org.rajendra;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArraysStream
{
    public <T> void Test()
    {
        Integer[] numbers = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] names = new String[]{"Raj", "Aks", "Pat", "Shi", "Aks", "Pat"};
        Object[] objList = new Object[]{null, 1, "Shiv", 1, null, 1, 3, 4, 2, 4, 2, 4, "Shiv", "Raj"};

        //AnyMatchGeneric(objList, "Shiv");
        //System.out.println(Arrays.toString(Distinct(names)));
        //ForEachElements(objList, "Shiv");
        //FilterElements(objList);

    }

    public <T> void FilterElements(T[] inputArray)
    {
        Integer sum = Arrays.stream(inputArray)
                .filter(element -> element instanceof Integer
                        && (Integer)element >= 4)
                .mapToInt(element -> (Integer) element)
                .sum();
        System.out.println("Sum of integers: " + sum);
    }

    public <T> void ForEachElements(T[] inputArray, T target)
    {
        Arrays.stream(inputArray).forEach(element -> checkForTarget(element, target));
    }
    public <T> T[] Distinct(T[] inputArray)
    {
        return Arrays.stream(inputArray)
                .distinct()
                .toArray(size -> Arrays.copyOf(inputArray, size));
    }
    public <T> void AnyMatchGeneric(T[] inputArray, T target)
    {
        boolean result = Arrays.stream(inputArray).anyMatch(n -> n == target);
        System.out.println("Does list contains " + target + " , statement is : " + result);
    }
    public void AllElementsMatch(Integer[] numbers)
    {
        Integer target = 0;
        boolean result = Arrays.stream(numbers).allMatch(n -> n > target);
        System.out.println("All number are greater " + target + " statement is : " + result);
    }
    public void sum(Integer[] numbers)
    {
        Integer Sum = Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum of even number " + Sum);
    }
    public void count(Integer[] numbers)
    {
        Long count = Arrays.stream(numbers).count();

        System.out.println("Count of array " + count);

    }
    public <T> void checkForTarget(T input, T target)
    {
        if (input == target)
        {
            System.out.println("List contains the target");
        }
    }
}

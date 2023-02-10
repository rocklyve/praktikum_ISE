package edu.kit.informatik.listings;

import java.util.List;

public class ObjectQ {
    public void foo() {
        // Example 2:
        List<Object> fruits = List.of("Apple", "Banana", "Cherry");
        Object fruitDessert = fruits.get(0) + " Pie";

        System.out.println(fruitDessert); // prints "Apple Pie"
    }
}
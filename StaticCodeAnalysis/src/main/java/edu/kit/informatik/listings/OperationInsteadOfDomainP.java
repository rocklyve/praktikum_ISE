package edu.kit.informatik.listings;

import edu.kit.informatik.listings.Food;

import java.util.List;

abstract class Animal {
    public void feed(List<Food> meal) { /* feed */ }
}
class Tiger extends Animal {
    public void feed(List<Food> meal) {
        // ensure safety
        super.feed(meal);
    }
}
class Donkey extends Animal {
    public void feed(List<Food> meal) {
        super.feed(meal);
        // pet the donkey
    }
}
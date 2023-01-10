abstract class Animal { /* ... */ }
class Tiger extends Animal { /* ... */ }
class Donkey extends Animal { /* ... */ }

class AnimalFeeding {
    public void feed(Tiger tiger, List<Food> tigerMeal) {
        // carefully feed the tiger without getting too close
    }

    public void feed(Donkey donkey, List<Food> donkeyMeal) {
        // feed and pet the donkey
    }
}

package edu.kit.informatik;

public class MissingThrowInMethodSignatureBadExample {
    // throw missing in method signature
    public void throwTestMethod() {
        throw new IllegalStateException();
    }
}

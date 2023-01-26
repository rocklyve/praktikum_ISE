package edu.kit.informatik;

public class ExceptionForControlFlow {
    public boolean assignValue(String number) {
        try {
            int value = Integer.parseInt(number);
        } catch (NumberFormatException e) {

        }
        /* ... */
        return true;
    }
}

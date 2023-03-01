package edu.kit.informatik;

public class TryCatchBlockTooLong {
    public int assignValue(String number) {
        int value = 0;
        try {
            value = Integer.parseInt(number);
            System.out.println("This is inside a try catch block");
            value += 1;
            value += 2;
            value += 3;
            value += 1;
            value += 2;
            value += 3;
            value += 1;
            value += 2;
            value += 3;
            value += 1;
            value += 2;
            value += 3;
        } catch (NumberFormatException e) {
            return value;
        }
        /* ... */
        return value;
    }
}

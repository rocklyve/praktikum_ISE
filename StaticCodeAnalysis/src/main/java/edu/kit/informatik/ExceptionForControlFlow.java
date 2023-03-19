package edu.kit.informatik;

public class ExceptionForControlFlow {
    public boolean parseValueGoodExample(String number) {
        if (!number.matches("\\d+")) {
            System.out.println("Invalid input. Please enter a valid number.");
        } else {
            int value = Integer.parseInt(number);
            System.out.println("The value is: " + value);
        }
        return true;
    }

    public boolean parseValueBadExample(String number) {
        try {
            int value = Integer.parseInt(number);
            System.out.println("The value is: " + value);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
        return true;
    }
}

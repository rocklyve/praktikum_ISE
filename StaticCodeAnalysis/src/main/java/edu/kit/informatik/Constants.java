package edu.kit.informatik;

import java.util.List;

public class Constants {

    public static final int MATRIX_CONSTANT_SIZE = 4;
    public static final int MATRIX_CONSTANT_SIZE_2 = 4;
    public static final int MATRIX_CONSTANT_SIZE_3 = 4;

    private final String menuHeader = "Menu:";
    private final String item = "\n - ";
    private static List<String> dishes;

    public void updateDishes(List<String> newDishes) {
        dishes = newDishes; // daily update
    }
    public String menuText() {
        String menu = menuHeader;
        for (String dish : dishes) {
            menu += item + dish;
        }
        return menu;
    }
}
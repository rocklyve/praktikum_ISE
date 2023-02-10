package edu.kit.informatik.listings;

import java.util.List;

public class StaticDynamicAttributesP {
    private static final String MENU_HEADER = "Menu:";
    private static final String ITEM = "\n - ";
    private List<String> dishes;

    public void updateDishes(List<String> newDishes) {
        dishes = newDishes; // daily update
    }

    public String menuText() {
        String menu = MENU_HEADER;
        for (String dish : dishes) {
            menu += ITEM + dish;
        }
        return menu;
    }
}
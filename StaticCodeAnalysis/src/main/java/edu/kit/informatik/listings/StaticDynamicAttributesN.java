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
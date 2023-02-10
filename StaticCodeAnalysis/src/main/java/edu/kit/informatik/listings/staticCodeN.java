package edu.kit.informatik.listings;

import edu.kit.informatik.Product;

import java.util.List;

public class staticCodeN {
    private static int idCounter = 0;
    private final int id;
    private final List<Product> products;
    public staticCodeN(List<Product> products) {
        this.id = idCounter++;
        this.products = products;
    }
    private static double getTotalPrice(List<Product> products) {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
}

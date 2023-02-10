package edu.kit.informatik.listings;

import edu.kit.informatik.Product;

import java.util.List;

public class staticCodeP {
    private final int id;
    private final List<Product> products;
    public staticCodeP(int id, List<Product> products) {
        this.id = id;
        this.products = products;
    }
    private double getTotalPrice() {
        double total = 0;
        for (Product product : this.products) {
            total += product.getPrice();
        }
        return total;
    }
}

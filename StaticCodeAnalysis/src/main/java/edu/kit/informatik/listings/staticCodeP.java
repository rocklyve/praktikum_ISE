public class Receipt {
    private final int id;
    private final List<Product> products;
    public Receipt(int id, List<Product> products) {
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

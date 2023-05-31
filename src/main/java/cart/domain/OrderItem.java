package cart.domain;

public class OrderItem {
    Product product;
    int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return product.getSalePrice() * quantity;
    }
}

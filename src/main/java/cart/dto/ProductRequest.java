package cart.dto;

public class ProductRequest {
    private String name;
    private int price;
    private int sale;
    private String imageUrl;

    public ProductRequest() {
    }

    public ProductRequest(String name, int price, int sale, String imageUrl) {
        this.name = name;
        this.price = price;
        this.sale = sale;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getSale() {
        return sale;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

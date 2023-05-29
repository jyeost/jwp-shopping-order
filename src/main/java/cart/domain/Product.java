package cart.domain;

public class Product {
    private Long id;
    private String name;
    private int price;
    private int sale;
    private String imageUrl;

    public Product(String name, int price, int sale,String imageUrl) {
        this.name = name;
        this.price = price;
        this.sale = sale;
        this.imageUrl = imageUrl;
    }

    public Product(Long id, String name, int price, int sale, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sale = sale;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
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

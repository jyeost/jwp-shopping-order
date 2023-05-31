package cart.entity;

public class CartItemEntity {

    private final Long id;
    private final int quantity;
    private final Long productId;
    private final Long memberId;

    public CartItemEntity(
            final Long id,
            final int quantity,
            final Long productId,
            final Long memberId
    ) {
        this.id = id;
        this.quantity = quantity;
        this.productId = productId;
        this.memberId = memberId;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getMemberId() {
        return memberId;
    }
}
package cart.dto;

public class OrderResponse {
    private final int addedPoint;
    private final int remainPoint;

    public OrderResponse(int addedPoint, int remainPoint) {
        this.addedPoint = addedPoint;
        this.remainPoint = remainPoint;
    }

    public int getAddedPoint() {
        return addedPoint;
    }

    public int getRemainPoint() {
        return remainPoint;
    }
}

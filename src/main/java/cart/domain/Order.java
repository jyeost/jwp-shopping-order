package cart.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Order {
    Point usedpoint;
    List<OrderItem> orderItem;

    public Order(Point usedpoint, List<OrderItem> orderItem) {
        this.usedpoint = usedpoint;
        this.orderItem = orderItem;
    }

    public static Order from(Point usedPoint, List<CartItem> cartItems) {
        List<OrderItem> orderItems = cartItems.stream().map(orderItem -> new OrderItem(orderItem.getProduct(), orderItem.getQuantity())).collect(Collectors.toList());
        return new Order(usedPoint, orderItems);
    }

    public Point getUsedpoint() {
        return usedpoint;
    }

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public Point getAdditionalPoint() {
        int price = getTotalPrice();
        Point point = new Point(price);
        return point.subtract(usedpoint);
    }

    private int getTotalPrice() {
        int sum = orderItem.stream()
                .mapToInt(OrderItem::getPrice)
                .sum();
        return (int) (sum * 2.5 / 100);
    }
}

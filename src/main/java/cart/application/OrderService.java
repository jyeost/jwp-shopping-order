package cart.application;

import cart.dao.CartItemDao;
import cart.domain.CartItem;
import cart.domain.Member;
import cart.domain.Order;
import cart.domain.Point;
import cart.dto.OrderRequest;
import cart.dto.OrderResponse;
import cart.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final CartItemDao cartItemDao;
    private final OrderRepository orderRepository;

    public OrderService(CartItemDao cartItemDao, OrderRepository orderRepository) {
        this.cartItemDao = cartItemDao;
        this.orderRepository = orderRepository;
    }

    public OrderResponse add(Member member, OrderRequest orderRequest) {
        List<CartItem> cartItems = cartItemDao.findByCartItemIds(orderRequest.getCartItemIds());
        cartItems.forEach(orderItem -> orderItem.checkOwner(member));

        Point usedPoint = new Point(orderRequest.getUsePoint());
        member.checkPoint(usedPoint);
        Order order = Order.from(usedPoint, cartItems);
        Point additionalPoint = order.getAdditionalPoint();

        orderRepository.save(member, order);

        // 포인트 업데이트 하기

        return new OrderResponse(additionalPoint.getPoint(), member.getPoint().getPoint() - usedPoint.getPoint() + additionalPoint.getPoint());
    }
}

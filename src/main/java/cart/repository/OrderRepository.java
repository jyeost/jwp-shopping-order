package cart.repository;

import cart.dao.OrderDao;
import cart.dao.OrderItemDao;
import cart.domain.Member;
import cart.domain.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    OrderDao orderDao;
    OrderItemDao orderItemDao;

    public OrderRepository(OrderDao orderDao, OrderItemDao orderItemDao) {
        this.orderDao = orderDao;
        this.orderItemDao = orderItemDao;
    }

    public void save(Member member, Order order) {
        Long orderId = orderDao.save(member, order);
        orderItemDao.save(orderId, order.getOrderItem());
    }
}

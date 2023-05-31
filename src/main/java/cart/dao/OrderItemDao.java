package cart.dao;

import cart.domain.OrderItem;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderItemDao {

    private final JdbcTemplate jdbcTemplate;

    public OrderItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Long orderId, List<OrderItem> orderItems) {
        String sql = "INSERT INTO order_item (order_id, product_id, quantity, price, sale) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, orderId);
                ps.setLong(2, orderItems.get(i).getProduct().getId());
                ps.setLong(3, orderItems.get(i).getQuantity());
                ps.setLong(4, orderItems.get(i).getProduct().getPrice());
                ps.setLong(5, orderItems.get(i).getProduct().getSale());
            }

            @Override
            public int getBatchSize() {
                return orderItems.size();
            }
        });
        orderItems.clear();
    }
}

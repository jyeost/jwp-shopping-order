package cart.dao;

import cart.domain.Member;
import cart.domain.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public OrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("orders")
                .usingGeneratedKeyColumns("id")
                .usingColumns("member_id", "used_point");
    }


    public Long save(Member member, Order order) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection.prepareStatement(
//                    "INSERT INTO orders (member_id, used_point) VALUES (?, ?)",
//                    Statement.RETURN_GENERATED_KEYS
//            );
//
//            ps.setLong(1, member.getId());
//            ps.setLong(2, order.getUsedpoint().getPoint());
//
//            return ps;
//        }, keyHolder);
//
//        return Objects.requireNonNull(keyHolder.getKey()).longValue();

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("member_id", member.getId())
                .addValue("used_point", order.getUsedpoint().getPoint());

        return simpleJdbcInsert.executeAndReturnKey(params).longValue();
    }
}

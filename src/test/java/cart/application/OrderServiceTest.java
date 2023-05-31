package cart.application;

import cart.dao.CartItemDao;
import cart.dao.MemberDao;
import cart.dao.ProductDao;
import cart.domain.Member;
import cart.domain.Product;
import cart.dto.OrderRequest;
import cart.dto.OrderResponse;
import cart.integration.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class OrderServiceTest extends IntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private CartItemDao cartItemDao;

    @Autowired
    private ProductDao productDao;

    private Product product;
    private Product product2;
    private Member member;
    private Member member2;

    @BeforeEach
    void setUp() {
        product = productDao.getProductById(1L);
        product2 = productDao.getProductById(2L);

        member = memberDao.getMemberById(1L);
        member2 = memberDao.getMemberById(2L);
    }

    @Test
    void aa() {
        OrderRequest orderRequest = new OrderRequest(List.of(product.getId(), product2.getId()), 0);

        OrderResponse add = orderService.add(member, orderRequest);
        System.out.println("추가 포인트" + add.getAddedPoint() + "남은 포인트" + add.getRemainPoint());
    }

}
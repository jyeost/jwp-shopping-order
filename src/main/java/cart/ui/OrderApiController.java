package cart.ui;

import cart.application.OrderService;
import cart.domain.Member.Member;
import cart.dto.request.OrderRequest;
import cart.dto.response.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderApiController {
    private final OrderService orderService;

    public OrderApiController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    ResponseEntity<Void> addOrder(@RequestBody @Valid OrderRequest orderRequest, Member member) {
        Long orderId = orderService.add(member, orderRequest);
        return ResponseEntity.created(URI.create("/orders/" + orderId)).build();
    }

    @GetMapping
    ResponseEntity<List<OrderResponse>> showAllOrderByMember(Member member) {
        List<OrderResponse> allOrdersByMember = orderService.findByMember(member);
        return ResponseEntity.ok().body(allOrdersByMember);
    }

    @GetMapping("/{id}")
    ResponseEntity<OrderResponse> showOrderByOrderId(Member member, @PathVariable Long id) {
        OrderResponse order = orderService.findById(id, member);
        return ResponseEntity.ok().body(order);
    }
}

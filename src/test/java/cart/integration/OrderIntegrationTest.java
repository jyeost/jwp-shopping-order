package cart.integration;

import cart.domain.Member.Member;
import cart.dto.OrderItemResponse;
import cart.dto.OrderRequest;
import cart.dto.OrderResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OrderIntegrationTest extends IntegrationTest {

    private final Member member1 = new Member(1L, "a@a.com", "1234");
    private final Member member2 = new Member(2L, "b@b.com", "1234");

    @Test
    @DisplayName("사용자는 장바구니에 있는 상품을 선택해 주문할 수 있다.")
    void order_success() {
        // given, when
        ExtractableResponse<Response> orderCreateResponse = createOrder(List.of(1L), member1);

        // then
        assertThat(orderCreateResponse.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(orderCreateResponse.header("Location")).contains("/orders/1");
    }

    @Test
    @DisplayName("사용자는 전체 주문 내역을 확인할 수 있다.")
    void findAllOrderHistories_success() {
        // given
        createOrder(List.of(1L), member1);
        createOrder(List.of(2L), member1);
        Timestamp time = Timestamp.valueOf(LocalDateTime.of(2023, 6, 1, 2, 45, 00));

        // when
        ExtractableResponse<Response> orderHistoryResponse = findOrderHistoryOfMember(member1);

        // then
        assertThat(orderHistoryResponse.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(orderHistoryResponse.jsonPath().getInt("size()")).isEqualTo(2);
        assertThat(orderHistoryResponse.jsonPath().getObject("[0]", OrderResponse.class))
                .usingRecursiveComparison()
                .ignoringFields("orderDate")
                .isEqualTo(new OrderResponse(1L, 20_000, time,
                        List.of(
                                new OrderItemResponse(1L, 2, "치킨", 10_000, "chickenImg")
                        )));
        assertThat(orderHistoryResponse.jsonPath().getObject("[1]", OrderResponse.class))
                .usingRecursiveComparison()
                .ignoringFields("orderDate")
                .isEqualTo(new OrderResponse(2L, 80_000, time,
                        List.of(
                                new OrderItemResponse(2L, 4, "샐러드", 20_000, "saladImg")
                        )));
    }

    @Test
    @DisplayName("사용자는 상세 주문 내역을 확인할 수 있다.")
    void findDetailOrderHistory_success() {
        // given
        createOrder(List.of(1L, 2L), member1);

        // when
        ExtractableResponse<Response> detailOrderHistory = findDetailOrderHistory(1L, member1);

        // then
        assertThat(detailOrderHistory.jsonPath().getObject(".", OrderResponse.class))
                .usingRecursiveComparison()
                .ignoringFields("orderDate")
                .isEqualTo(new OrderResponse(1L, 100_000, Timestamp.valueOf(LocalDateTime.now()),
                        List.of(
                                new OrderItemResponse(1L, 2, "치킨", 10_000, "chickenImg"),
                                new OrderItemResponse(2L, 4, "샐러드", 20_000, "saladImg")
                        )));
    }

    @Test
    @DisplayName("자신의 장바구니에 없는 장바구니 상품을 주문하면 주문에 실패한다.")
    void order_noCartItemFound_fail() {
        // when
        ExtractableResponse<Response> orderCreateResponse = createOrder(List.of(3L), member1);

        // then
        assertThat(orderCreateResponse.statusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    private ExtractableResponse<Response> createOrder(List<Long> cartItemIds, Member member) {
        OrderRequest orderRequest = new OrderRequest(cartItemIds, 0);

        return given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().preemptive().basic(member.getEmail().email(), member.getPassword().password())
                .body(orderRequest)
                .when()
                .post("/orders")
                .then()
                .log().all()
                .extract();
    }

    private ExtractableResponse<Response> findOrderHistoryOfMember(Member member) {
        return given()
                .auth().preemptive().basic(member.getEmail().email(), member.getPassword().password())
                .when()
                .get("/orders")
                .then()
                .extract();
    }

    private ExtractableResponse<Response> findDetailOrderHistory(long orderId, Member member) {
        return given()
                .auth().preemptive().basic(member.getEmail().email(), member.getPassword().password())
                .pathParam("id", orderId)
                .when()
                .get("/orders/{id}")
                .then()
                .extract();
    }
}


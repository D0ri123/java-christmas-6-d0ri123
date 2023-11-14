package christmas.model.order;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderServiceTest {

    @Test
    void Order를_생성한다() {
        //given
        OrderService orderService = new OrderService("바비큐립-2");

        //when
        Order order = orderService.getOrder();

        //then
        assertEquals(order.getMenu(), "바비큐립");
        assertEquals(order.getQuantity(), 2);
    }
    
    //TODO: 만약 입력이 잘못 들어오면?

}
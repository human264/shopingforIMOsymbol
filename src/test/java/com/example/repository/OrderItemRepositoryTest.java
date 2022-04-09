package com.example.repository;

import com.example.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class OrderItemRepositoryTest {

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Test
    public void test01() {
        List<OrderItem> orderItemByOrderId = orderItemRepository.findOrderItemByOrderId(10L);
        for (OrderItem orderItem : orderItemByOrderId) {
            System.out.println("orderItem = " + orderItem.toString());

        }
    }

}
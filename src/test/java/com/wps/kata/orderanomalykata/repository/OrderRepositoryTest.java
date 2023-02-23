package com.wps.kata.orderanomalykata.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.wps.kata.orderanomalykata.Application;
import com.wps.kata.orderanomalykata.model.Order;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@Sql("/sql/default_orders_and_anomalies.sql")
@RequiredArgsConstructor
@Transactional
class OrderRepositoryTest {

  @Autowired
  private OrderRepository orderRepository;

  @Test
  void testFindAll() {
    // When
    List<Order> orders = orderRepository.findAll();

    // Then
    assertThat(orders).hasSize(5)
            .extracting(Order::getOrderId)
            .containsExactlyInAnyOrder(1L, 2L, 3L, 4L, 5L);
  }

}
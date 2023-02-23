package com.wps.kata.orderanomalykata.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wps.kata.orderanomalykata.dto.OrderDto;
import com.wps.kata.orderanomalykata.model.Order;
import com.wps.kata.orderanomalykata.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

  @Mock
  private OrderRepository orderRepository;
  @Mock
  private AnomalyService anomalyService;
  @InjectMocks
  private OrderService orderService;

  @Test
  void createOrder_shouldReturnCreatedIdAndCallAnomalyService() {
    // Given
    OrderDto shoeOrder = OrderDto.builder()
            .type("shoe")
            .quantity(2)
            .build();
    Order savedOrder = new Order(1L, "shoe", 2);
    when(orderRepository.save(any())).thenReturn(savedOrder);

    // When
    Long orderId = orderService.createOrder(shoeOrder);

    // Then
    assertThat(orderId).isEqualTo(savedOrder.getOrderId());
    verify(anomalyService).checkOrder(savedOrder);
  }
}
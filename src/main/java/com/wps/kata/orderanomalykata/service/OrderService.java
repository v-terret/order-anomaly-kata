package com.wps.kata.orderanomalykata.service;

import com.wps.kata.orderanomalykata.dto.OrderDto;
import com.wps.kata.orderanomalykata.model.Order;
import com.wps.kata.orderanomalykata.repository.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final AnomalyService anomalyService;


  @Transactional
  public Long createOrder(OrderDto order) {
    Order savedOrder = orderRepository.save(new Order(order.getType(), order.getQuantity()));
    anomalyService.checkOrder(savedOrder);
    return savedOrder.getOrderId();
  }

  @Transactional
  public List<OrderDto> findAllOrders() {
    return orderRepository.findAll().stream()
            .map(entity -> OrderDto.builder()
                    .id(entity.getOrderId())
                    .type(entity.getClothingType())
                    .quantity(entity.getItemNumber())
                    .build())
            .collect(Collectors.toList());
  }
}

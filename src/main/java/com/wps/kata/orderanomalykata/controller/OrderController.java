package com.wps.kata.orderanomalykata.controller;

import com.wps.kata.orderanomalykata.dto.OrderDto;
import com.wps.kata.orderanomalykata.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public Long createOrder(@RequestBody OrderDto order) {
    return orderService.createOrder(order);
  }

  @GetMapping()
  public List<OrderDto> findAll() {
    return orderService.findAllOrders();
  }

}

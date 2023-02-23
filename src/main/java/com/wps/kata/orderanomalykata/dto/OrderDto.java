package com.wps.kata.orderanomalykata.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderDto {

  Long id;
  String type;
  int quantity;

}

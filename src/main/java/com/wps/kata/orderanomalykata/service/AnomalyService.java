package com.wps.kata.orderanomalykata.service;

import com.wps.kata.orderanomalykata.model.Anomaly;
import com.wps.kata.orderanomalykata.model.AnomalyType;
import com.wps.kata.orderanomalykata.model.Order;
import com.wps.kata.orderanomalykata.repository.AnomalyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnomalyService {

  public static final String INCORRECT_NUMBER_OF_ITEMS = "Incorrect number of items";
  public static final String MAX_LIMIT_OF_ITEMS_EXCEEDED = "Max limit of items exceeded";
  private final AnomalyRepository anomalyRepository;

  public void checkOrder(Order order) {
    if (order.getClothingType().equalsIgnoreCase("shoe") && order.getItemNumber() % 2 != 0) {
      anomalyRepository.save(new Anomaly(null, order, INCORRECT_NUMBER_OF_ITEMS, AnomalyType.OPEN));
    }
    if (order.getItemNumber() > 10) {
      anomalyRepository.save(new Anomaly(null, order, MAX_LIMIT_OF_ITEMS_EXCEEDED, AnomalyType.OPEN));
    }
  }

}

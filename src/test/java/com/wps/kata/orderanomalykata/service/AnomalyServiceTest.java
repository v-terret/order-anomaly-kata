package com.wps.kata.orderanomalykata.service;

import static com.wps.kata.orderanomalykata.service.AnomalyService.INCORRECT_NUMBER_OF_ITEMS;
import static com.wps.kata.orderanomalykata.service.AnomalyService.MAX_LIMIT_OF_ITEMS_EXCEEDED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import com.wps.kata.orderanomalykata.model.Anomaly;
import com.wps.kata.orderanomalykata.model.AnomalyType;
import com.wps.kata.orderanomalykata.model.Order;
import com.wps.kata.orderanomalykata.repository.AnomalyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AnomalyServiceTest {

  @Mock
  private AnomalyRepository anomalyRepository;
  @InjectMocks
  private AnomalyService anomalyService;
  @Captor
  ArgumentCaptor<Anomaly> anomalyArgumentCaptor;

  @Test
  void checkOrder_whenNoProblemOccurs_shouldNotCreateAnomaly() {
    // Given
    Order order = new Order(1L, "t-shirt", 5);

    // When
    anomalyService.checkOrder(order);

    // Then
    verifyNoInteractions(anomalyRepository);
  }

  @Test
  void checkOrder_when3ShoesOrdered_shouldCreateOneAnomaly() {
    // Given
    Order order = new Order(1L, "shoe", 3);

    // When
    anomalyService.checkOrder(order);

    // Then
    verify(anomalyRepository).save(anomalyArgumentCaptor.capture());
    assertThat(anomalyArgumentCaptor.getAllValues())
            .hasSize(1)
            .first()
            .extracting("anomalyType", "status")
            .contains(INCORRECT_NUMBER_OF_ITEMS, AnomalyType.OPEN);
  }

  @Test
  void checkOrder_when15ShoesOrdered_shouldCreateTwoAnomalies() {
    // Given
    Order order = new Order(1L, "shoe", 15);

    // When
    anomalyService.checkOrder(order);

    // Then
    verify(anomalyRepository, times(2)).save(anomalyArgumentCaptor.capture());
    assertThat(anomalyArgumentCaptor.getAllValues())
            .hasSize(2)
            .extracting(Anomaly::getAnomalyType)
            .containsExactlyInAnyOrder(INCORRECT_NUMBER_OF_ITEMS, MAX_LIMIT_OF_ITEMS_EXCEEDED);
  }

  @Test
  void checkOrder_when11TShirtOrdered_shouldCreateOneAnomaly() {
    // Given
    Order order = new Order(1L, "t-shirt", 15);

    // When
    anomalyService.checkOrder(order);

    // Then
    verify(anomalyRepository).save(anomalyArgumentCaptor.capture());
    assertThat(anomalyArgumentCaptor.getAllValues())
            .hasSize(1)
            .first()
            .extracting(Anomaly::getAnomalyType)
            .isEqualTo(MAX_LIMIT_OF_ITEMS_EXCEEDED);
  }



}
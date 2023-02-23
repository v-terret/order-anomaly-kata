package com.wps.kata.orderanomalykata.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_ORDER")
@Getter
@Setter
@NoArgsConstructor
public class Order {

  public Order(Long orderId, String clothingType, int itemNumber) {
    this.orderId = orderId;
    this.clothingType = clothingType;
    this.itemNumber = itemNumber;
  }

  public Order(String clothingType, int itemNumber) {
    this.clothingType = clothingType;
    this.itemNumber = itemNumber;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderId;
  @Column(nullable = false)
  private String clothingType;
  @Column(nullable = false)
  private int itemNumber;
  @OneToMany(mappedBy = "order")
  List<Anomaly> anomalyList;

}

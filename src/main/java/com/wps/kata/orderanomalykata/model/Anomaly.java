package com.wps.kata.orderanomalykata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_ANOMALY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Anomaly {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long annomalyId;

  @ManyToOne
  private Order order;

  @Column(nullable = false)
  private String anomalyType;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private AnomalyType status;

}

package com.wps.kata.orderanomalykata.repository;

import com.wps.kata.orderanomalykata.model.Anomaly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnomalyRepository extends JpaRepository<Anomaly, Long> {



}

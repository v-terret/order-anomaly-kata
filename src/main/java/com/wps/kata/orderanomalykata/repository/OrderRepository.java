package com.wps.kata.orderanomalykata.repository;

import com.wps.kata.orderanomalykata.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {



}

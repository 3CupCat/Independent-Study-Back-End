package com.taishow.dao;

import com.taishow.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    public Optional<Orders> findByOrderNum(String orderNum);
}
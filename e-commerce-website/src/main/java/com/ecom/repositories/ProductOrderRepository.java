package com.ecom.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.ProductOrder;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {
    List<ProductOrder> findByStatus(String status);

    public ProductOrder findByOrderId(String id);
    public List<ProductOrder> findByUserId(int id);
}

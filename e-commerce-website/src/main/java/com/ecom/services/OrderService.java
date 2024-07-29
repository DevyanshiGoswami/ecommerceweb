package com.ecom.services;

import com.ecom.model.OrderRequest;
import com.ecom.model.ProductOrder;
import com.ecom.repositories.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface OrderService {

    public void saveOrder(Integer userid,OrderRequest orderRequest);
    public List<ProductOrder> getOrdersByStatus(String status);
    public ProductOrder getOrderById(String id);

    public List<ProductOrder> getOrderByUserId(int userId);
    public List<ProductOrder> getAllOrders();
//    public List<ProductOrder> getAllOrders(String status);
}

package com.ecom.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.Cart;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    public Cart findByProductIdAndUserId(Integer productId, Integer userId);

}
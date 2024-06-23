package com.ecom.repositories;



import java.util.List;

import com.ecom.model.product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface productrepository extends JpaRepository<product, Integer> {

	List<product> findByIsActiveTrue();

	List<product> findByCategory(String category);

} 
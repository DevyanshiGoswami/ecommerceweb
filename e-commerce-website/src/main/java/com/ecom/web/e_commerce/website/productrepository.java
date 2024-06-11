package com.ecom.web.e_commerce.website;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface productrepository extends JpaRepository<product, Integer> {

	List<product> findByIsActiveTrue();

	List<product> findByCategory(String category);

} 
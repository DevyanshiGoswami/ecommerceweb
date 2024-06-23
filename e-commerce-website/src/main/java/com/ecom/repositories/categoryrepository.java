package com.ecom.repositories;

import java.util.List;


import com.ecom.model.category;
import org.springframework.data.jpa.repository.JpaRepository;



public interface categoryrepository extends JpaRepository<category, Integer> {

	public Boolean existsByName(String name);

	public List<category> findByIsActiveTrue();

}
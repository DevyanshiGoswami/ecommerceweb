package com.ecom.web.e_commerce.website;

import java.util.List;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface categoryrepository extends JpaRepository<category, Integer> {

	public Boolean existsByName(String name);

	public List<category> findByIsActiveTrue();

}
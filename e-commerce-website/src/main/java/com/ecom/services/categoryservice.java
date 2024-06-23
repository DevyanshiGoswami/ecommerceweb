package com.ecom.services;

import com.ecom.model.category;

import java.util.List;

public interface categoryservice {

	public category saveCategory(category Category);

	public Boolean existCategory(String name);

	public List<category> getAllCategory();

	public Boolean deleteCategory(int id);

	public category getCategoryById(int id);

	public List<category> getAllActiveCategory() ;

}
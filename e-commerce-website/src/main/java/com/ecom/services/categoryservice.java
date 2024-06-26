package com.ecom.services;

import com.ecom.model.Category;

import java.util.List;

public interface CategoryService {

	public Category saveCategory(Category Category);

	public Boolean existCategory(String name);

	public List<Category> getAllCategory();

	public Boolean deleteCategory(int id);

	public Category getCategoryById(int id);

	public List<Category> getAllActiveCategory() ;

}
package com.ecom.web.e_commerce.website;

import java.util.List;

public interface categoryservice {

	public category saveCategory(category Category);

	public Boolean existCategory(String name);

	public List<category> getAllCategory();

	public Boolean deleteCategory(int id);

	public category getCategoryById(int id);

	public List<category> getAllActiveCategory();
	
}
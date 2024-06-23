package com.ecom.services;



import java.util.List;

import com.ecom.model.category;
import com.ecom.repositories.categoryrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service
public class categoryserviceimpl implements categoryservice {

	@Autowired
	private categoryrepository categoryRepository;

	@Override
	public category saveCategory(category Category) {
		return categoryRepository.save(Category);
	}

	@Override
	public List<category> getAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public Boolean existCategory(String name) {
		return categoryRepository.existsByName(name);
	}

	@Override
	public Boolean deleteCategory(int id) {
		category category = categoryRepository.findById(id).orElse(null);

		if (!ObjectUtils.isEmpty(category)) {
			categoryRepository.delete(category);
			return true;
		}
		return false;
	}

	@Override
	public category getCategoryById(int id) {
		category Category = categoryRepository.findById(id).orElse(null);
		return Category;
	}

	@Override
	public List<category> getAllActiveCategory() {
		List<category> Categories = categoryRepository.findByIsActiveTrue();
		return Categories;
	}

}

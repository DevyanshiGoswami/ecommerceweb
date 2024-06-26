package com.ecom.services;



import java.util.List;

import com.ecom.model.Category;
import com.ecom.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category saveCategory(Category Category) {
		return categoryRepository.save(Category);
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public Boolean existCategory(String name) {
		return categoryRepository.existsByName(name);
	}

	@Override
	public Boolean deleteCategory(int id) {
		Category category = categoryRepository.findById(id).orElse(null);

		if (!ObjectUtils.isEmpty(category)) {
			categoryRepository.delete(category);
			return true;
		}
		return false;
	}

	@Override
	public Category getCategoryById(int id) {
		Category Category = categoryRepository.findById(id).orElse(null);
		return Category;
	}

	@Override
	public List<Category> getAllActiveCategory() {
		List<Category> Categories = categoryRepository.findByIsActiveTrue();
		return Categories;
	}

}

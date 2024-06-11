package com.ecom.web.e_commerce.website;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service
public class categoryserviceimpl implements categoryservice {

	@Autowired
	private categoryrepository categoryRepository;

	@Override
	public category saveCategory(category category) {
		return categoryRepository.save(category);
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
		category category = categoryRepository.findById(id).orElse(null);
		return category;
	}

	@Override
	public List<category> getAllActiveCategory() {
		List<category> categories = categoryRepository.findByIsActiveTrue();
		return categories;
	}

}

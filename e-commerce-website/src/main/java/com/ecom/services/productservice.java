package com.ecom.services;


import java.util.List;

import com.ecom.model.product;
import org.springframework.web.multipart.MultipartFile;



public interface productservice {

	public product saveProduct(product Product);

	public List<product> getAllProducts();

	public Boolean deleteProduct(Integer id);

	public product getProductById(Integer id);

	public product updateProduct(product product, MultipartFile file);

	public List<product> getAllActiveProducts(String category);
	
}

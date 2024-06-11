package com.ecom.web.e_commerce.website;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;



public interface productservice {

	public product saveProduct(product Product);

	public List<product> getAllProducts();

	public Boolean deleteProduct(Integer id);

	public product getProductById(Integer id);

	public product updateProduct(product product, MultipartFile file);

	public List<product> getAllActiveProducts(String category);
	
}

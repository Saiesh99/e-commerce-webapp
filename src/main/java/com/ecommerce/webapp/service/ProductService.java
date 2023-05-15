package com.ecommerce.webapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.webapp.entity.Product;
import com.ecommerce.webapp.repo.ProductRepo;

@Service
public class ProductService {
	@Autowired
	ProductRepo productRepo;

	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	public void addProduct(Product product) {
		productRepo.save(product);
	}

	public void deleteProductById(Long id) {
		productRepo.deleteById(id);
	}

	public Optional<Product> getProductById(Long id) {
		return productRepo.findById(id);
	}
	
	public List<Product> getProductByCategoryId(int id) {
		return productRepo.findAllByCategory_Id(id);
	}
}

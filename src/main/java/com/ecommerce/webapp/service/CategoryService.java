package com.ecommerce.webapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.webapp.entity.Category;
import com.ecommerce.webapp.repo.CategoryRepo;



@Service
public class CategoryService {

	@Autowired
	CategoryRepo categoryRepo;
	
	public List<Category> getAllCategories(){
		return categoryRepo.findAll();
	}
	
	public void addCategory(Category category) {
		categoryRepo.save(category);
	}
	
	public void deleteCategoryById(Integer id) {
		categoryRepo.deleteById(id);
	}
	
	public Optional<Category> getCategoryById(Integer id) {
		return categoryRepo.findById(id);
	}
}

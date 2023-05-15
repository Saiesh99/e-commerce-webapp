package com.ecommerce.webapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.webapp.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}

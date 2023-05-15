package com.ecommerce.webapp.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.webapp.dto.ProductDTO;
import com.ecommerce.webapp.entity.Category;
import com.ecommerce.webapp.entity.Product;
import com.ecommerce.webapp.service.CategoryService;
import com.ecommerce.webapp.service.ProductService;

@Controller
public class AdminController {
	
	public static String imageURL = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

	@Autowired
	CategoryService categoryService;

	
	@Autowired
	ProductService productService;

	
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}

	//categories
	@GetMapping("admin/categories")
	public String getCategories(Model model) {
		model.addAttribute("categories", categoryService.getAllCategories());
		return "categories";
	}

	@GetMapping("/admin/categories/add")
	public String getAddCategories(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}

	@PostMapping("/admin/categories/add")
	public String postAddCategories(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategoryById(@PathVariable(name = "id") Integer id) {
		categoryService.deleteCategoryById(id);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/update/{id}")
	public String updateCategoryById(@PathVariable(name = "id") Integer id, Model model) {
		Optional<Category> category = categoryService.getCategoryById(id);
		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		} else {
			return "404";
		}

	}
	
	//products
	@GetMapping("admin/products")
	public String getProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String getAddProducts(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories",categoryService.getAllCategories());
		return "productsAdd";
	}

	@PostMapping("/admin/products/add")
	public String postAddProducts(@ModelAttribute("productDTO") ProductDTO productDTO, @RequestParam("productImage") MultipartFile file,
			@RequestParam("imgName") String imgName) {
		Product product = new Product();
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
		product.setDescription(productDTO.getDescription());
		product.setId(productDTO.getId());
		//product.setImageName(productDTO.getImageName());
		product.setName(productDTO.getName());
		product.setWeight(productDTO.getWeight());
		product.setPrice(productDTO.getPrice());
		String imageUUID;
		if(!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(imageURL, imageUUID);
		}
		
		return "redirect:/admin/products";
	}
}

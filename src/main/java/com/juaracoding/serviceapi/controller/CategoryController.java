package com.juaracoding.serviceapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.serviceapi.entity.Categories;
import com.juaracoding.serviceapi.payload.response.MessageResponse;
import com.juaracoding.serviceapi.repository.CategoriesRepository;
import com.juaracoding.serviceapi.services.ModelCategory;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	ModelCategory modelCategory;
	
	@Autowired
	CategoriesRepository categoriesRepository;
	
	@GetMapping("/")
	public List<Categories> getAllCategories() {
	    
		return modelCategory.getAllCategories();
	    
	  }
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Categories> getCategoriesById(@PathVariable("id") Long id) {
	    Optional<Categories> categoriesData = categoriesRepository.findById(id);

	    if (categoriesData.isPresent()) {
	      return new ResponseEntity<>(categoriesData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	@PostMapping("/")
	public ResponseEntity<?> addCategory(@RequestBody Categories postCategory){
		
		Categories categories = new Categories(postCategory.getCategoryName(), postCategory.getDescription());
		
		categoriesRepository.save(categories);
		
		return ResponseEntity.ok(new MessageResponse("Category added successfully!"));
	}
	
	@PutMapping("/{id}")
	public String updateCategory(@PathVariable Long id, @RequestBody Categories updateCategory, Model model) {
		
		updateCategory.setId(id);
		Categories category = new Categories(updateCategory.getCategoryName(), updateCategory.getDescription());
		
		category.setId(id);
		this.categoriesRepository.save(category);
		
		return "Category updated successfully!";

	}
	
	@DeleteMapping("/{id}")
	public String deleteCategory(@PathVariable Long id, Model model) {
		this.modelCategory.deleteCategory(id);
		return "Category deleted successfully!";
	}
	
	
}

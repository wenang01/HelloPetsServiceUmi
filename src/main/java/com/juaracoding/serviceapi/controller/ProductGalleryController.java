package com.juaracoding.serviceapi.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.juaracoding.serviceapi.entity.ProductGalleries;
import com.juaracoding.serviceapi.repository.ProductGalleriesRepository;
import com.juaracoding.serviceapi.services.ModelProductGallery;
import com.juaracoding.serviceapi.utility.FileUtility;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/productGalleries")
public class ProductGalleryController {

	@Autowired 
	ModelProductGallery modelProductGallery;
	
	@Autowired
	ProductGalleriesRepository galleriesRepository;
	
	@GetMapping("/")
    public List<ProductGalleries> indexProductGalleries(Model model) {    	
    	model.addAttribute("lstProductGalleries",modelProductGallery.getAllProductImages());
    	return modelProductGallery.getAllProductImages();
    }
	
//	@GetMapping("products/{products_id}")
//	public List<ProductGalleries> getProductGalleryByProductId(@PathVariable("products_id") String products_id, Model model) {
//		model.addAttribute("lstGalleriesByProductId",modelProductGallery.getByProductsId(products_id));
//    	return modelProductGallery.getByProductsId(products_id);
//	  }
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductGalleries> getProductGalleryById(@PathVariable("id") Long id) {
	    Optional<ProductGalleries> productGalleryData = galleriesRepository.findById(id);

	    if (productGalleryData.isPresent()) {
	      return new ResponseEntity<>(productGalleryData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	@PostMapping("/")
	public String addProductGalleries(@RequestParam(value = "file")MultipartFile file, @ModelAttribute ProductGalleries productGalleries, Model model) throws IOException { {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	
		String uploadDir = "src/main/java/productGallery-photos/" ;
		
		FileUtility.saveFile(uploadDir, fileName, file);
		productGalleries.setImage("/"+uploadDir + fileName);
		this.modelProductGallery.addProductImages(productGalleries);
		model.addAttribute("listProductGallery",modelProductGallery.getAllProductImages());
		
		return "Product Gallery added successfully!";
	}
	}
	
	@PutMapping("/{id}")
	public String updateProductGalleries(@PathVariable String id, @RequestParam(value = "file")MultipartFile file, @ModelAttribute ProductGalleries productGalleries, Model model) throws IOException { {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	
		productGalleries.setId(Long.parseLong(id));
		
		String uploadDir = "src/main/java/productGallery-photos/" ;
		
		FileUtility.saveFile(uploadDir, fileName, file);
		productGalleries.setImage("/"+uploadDir + fileName);
		this.modelProductGallery.addProductImages(productGalleries);
		model.addAttribute("listProductGallery",modelProductGallery.getAllProductImages());
		
		return "Product Gallery updated successfully!";
	}
	}
	
	@DeleteMapping("/{id}")
    public String deleteProductGallery(@PathVariable String id, Model model) {
    	
    	this.modelProductGallery.deleteProductImage(id);
    	
		return "Product Gallery {id} deleted successfully";
    	
    }
	
}

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

import com.juaracoding.serviceapi.entity.Carts;
import com.juaracoding.serviceapi.entity.User;
import com.juaracoding.serviceapi.payload.response.MessageResponse;
import com.juaracoding.serviceapi.repository.CartsRepository;
import com.juaracoding.serviceapi.services.ModelCarts;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/carts")
public class CartsController {

	
	@Autowired
	ModelCarts modelCarts;
	
	@Autowired
	CartsRepository cartsRepository;
	
	@GetMapping("/")
	public List<Carts> getAllCarts() {
	    
		return modelCarts.getAllCarts();
	    
	  }
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Carts> getCartsById(@PathVariable("id") Long id) {
	    Optional<Carts> cartsData = cartsRepository.findById(id);

	    if (cartsData.isPresent()) {
	      return new ResponseEntity<>(cartsData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	@GetMapping("u/{userId}")
	public ResponseEntity<Carts> getCartsByUserId(@PathVariable("userId") String userId) {
	    Optional<Carts> cartsData = cartsRepository.findByUserId(Long.parseLong(userId));

	    if (cartsData.isPresent()) {
	      return new ResponseEntity<>(cartsData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	@PostMapping("/")
	public ResponseEntity<?> addCarts(@RequestBody Carts postCarts){
		
		Carts carts = new Carts(postCarts.getUser(), postCarts.getProduct(), postCarts.getQty());
		
		cartsRepository.save(carts);
		
		return ResponseEntity.ok(new MessageResponse("Carts added successfully!"));
	}
	
	@PutMapping("/{id}")
	public String updateCarts(@PathVariable Long id, @RequestBody Carts updateCarts, Model model) {
		
		updateCarts.setId(id);
		Carts carts = new Carts(updateCarts.getUser(), updateCarts.getProduct(), updateCarts.getQty());
		
		carts.setId(id);
		this.cartsRepository.save(carts);
		
		return "Carts updated successfully!";

	}
	
	@DeleteMapping("/{id}")
	public String deleteCarts(@PathVariable Long id, Model model) {
		this.modelCarts.deleteCart(id);
		return "Carts deleted successfully!";
	}
}

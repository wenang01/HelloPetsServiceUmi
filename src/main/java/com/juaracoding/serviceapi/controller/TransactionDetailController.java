package com.juaracoding.serviceapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.serviceapi.entity.TransactionDetails;
import com.juaracoding.serviceapi.payload.response.MessageResponse;
import com.juaracoding.serviceapi.repository.TransactionDetailsRepository;
import com.juaracoding.serviceapi.services.ModelTransactionDetail;

@RestController
@RequestMapping("/transactionDetails")
public class TransactionDetailController {

	@Autowired 
	ModelTransactionDetail modelTransactionDetail;
	
	@Autowired
	TransactionDetailsRepository transactionDetailsRepo;
	
	@GetMapping("/")
    public List<TransactionDetails> indexTransactionDetails(Model model) {    	
    	model.addAttribute("lstTransactionDetails",modelTransactionDetail.getAllTransactionDetails());
    	return modelTransactionDetail.getAllTransactionDetails();
    }
	
	@PostMapping("/add")
	public ResponseEntity<?> addTransactionDetail(@ModelAttribute TransactionDetails postTransactionDetails, Model model){
		
//		TransactionDetails transactionDetails = new TransactionDetails(postTransactionDetails.getPrice(), 
//				postTransactionDetails.getShippingStatus(), postTransactionDetails.getResi(), 
//				postTransactionDetails.getCode()
//				);
//		transactionDetailsRepo.save(transactionDetails);

		this.modelTransactionDetail.addTransactionDetail(postTransactionDetails);
		model.addAttribute("ListTransactionDetails", modelTransactionDetail.getAllTransactionDetails());
		return ResponseEntity.ok(new MessageResponse("Transactions Details added successfully!"));
	}
	
	@PutMapping("/update/{id}")
	public String updateTransactionDetail(@PathVariable String id, @ModelAttribute TransactionDetails updateTransactionDetails, Model model) {
		
//		TransactionDetails transactionDetails = new TransactionDetails(updateTransactionDetails.getPrice(), 
//				updateTransactionDetails.getShippingStatus(), updateTransactionDetails.getResi(),
//				updateTransactionDetails.getCode());		
//		transactionDetails.setId(Long.parseLong(id));
//		this.transactionDetailsRepo.save(transactionDetails);
		
		this.modelTransactionDetail.addTransactionDetail(updateTransactionDetails);
		model.addAttribute("ListTransactionDetails", modelTransactionDetail.getAllTransactionDetails());

		return "Transaction Details updates successfully";

	}
	
	@GetMapping("/delete/{id}")
    public String deleteTransactionDetails(@PathVariable String id, Model model) {
    	
    	this.modelTransactionDetail.deleteTransactionDetail(id);
    	
		return "Transaction Details deleted successfully";
    	
    }
}

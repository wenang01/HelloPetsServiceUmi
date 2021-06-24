package com.juaracoding.serviceapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.serviceapi.entity.Transactions;
import com.juaracoding.serviceapi.repository.TransactionsRepository;
import com.juaracoding.serviceapi.services.ModelTransaction;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired 
	ModelTransaction modelTransaction;
	
	@Autowired
	TransactionsRepository transactionsRepo;

	@GetMapping("/")
    public List<Transactions> indexTransactions(Model model) {    	
    	model.addAttribute("lstTransactions",modelTransaction.getAllTransaction());
    	return modelTransaction.getAllTransaction();
    }
	
	@PostMapping("/add")
	public String addTransaction(@ModelAttribute Transactions postTransaction, Model model){

		this.modelTransaction.addTransaction(postTransaction);
		model.addAttribute("ListTransactions",modelTransaction.getAllTransaction());
		
		return "Transactions added successfully!";
	}
	
	@PutMapping("/update/{id}")
	public String updateTransaction(@PathVariable String id, @ModelAttribute Transactions updateTransaction, Model model) {
		
		updateTransaction.setId(Long.parseLong(id));

		this.modelTransaction.addTransaction(updateTransaction);
		model.addAttribute("ListTransactions",modelTransaction.getAllTransaction());
		return "Transaction updates successfully";

	}
	
	@GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable String id, Model model) {
    	
    	this.modelTransaction.deleteTransaction(id);
    	
		return "Transaction deleted successfully";
    	
    }
}

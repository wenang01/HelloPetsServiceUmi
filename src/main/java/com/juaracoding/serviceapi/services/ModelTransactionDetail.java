package com.juaracoding.serviceapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juaracoding.serviceapi.entity.TransactionDetails;
import com.juaracoding.serviceapi.repository.TransactionDetailsRepository;

@Service
public class ModelTransactionDetail implements ModelTransactionDetailInterface {

	@Autowired
	TransactionDetailsRepository transactionDetailRepo;
	@Override
	public List<TransactionDetails> getAllTransactionDetails() {
		// TODO Auto-generated method stub
		return (List<TransactionDetails>) this.transactionDetailRepo.findAll();
	}

	@Override
	public TransactionDetails addTransactionDetail(TransactionDetails transactionsDetails) {
		// TODO Auto-generated method stub
		return this.transactionDetailRepo.save(transactionsDetails);
	}

	@Override
	public void deleteTransactionDetail(String id) {
		// TODO Auto-generated method stub
		this.transactionDetailRepo.deleteById(Long.parseLong(id));
	}

	@Override
	public TransactionDetails getTransactionDetailById(String id) {
		// TODO Auto-generated method stub
		return this.transactionDetailRepo.findById(Long.parseLong(id)).get();
	}

}

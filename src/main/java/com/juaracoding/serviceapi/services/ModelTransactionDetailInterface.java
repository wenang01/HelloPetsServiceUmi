package com.juaracoding.serviceapi.services;

import java.util.List;

import com.juaracoding.serviceapi.entity.TransactionDetails;

public interface ModelTransactionDetailInterface {

	List<TransactionDetails> getAllTransactionDetails();
	
	public TransactionDetails addTransactionDetail(TransactionDetails transactionsDetails);
	
	public void deleteTransactionDetail(String id);
	
	public TransactionDetails getTransactionDetailById(String id);
}

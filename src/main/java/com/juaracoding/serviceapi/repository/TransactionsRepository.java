package com.juaracoding.serviceapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.juaracoding.serviceapi.entity.Transactions;

public interface TransactionsRepository extends CrudRepository<Transactions, Long> {

}

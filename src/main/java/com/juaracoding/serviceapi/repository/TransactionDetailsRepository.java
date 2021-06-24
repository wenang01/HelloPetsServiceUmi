package com.juaracoding.serviceapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.juaracoding.serviceapi.entity.TransactionDetails;

public interface TransactionDetailsRepository extends CrudRepository<TransactionDetails, Long> {

}

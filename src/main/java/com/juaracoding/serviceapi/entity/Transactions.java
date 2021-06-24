package com.juaracoding.serviceapi.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(	name = "transactions")
public class Transactions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int insurancePrice;
	private int shippingPrice;
	private int totalPrice;
	private String transactionStatus;
	private String code;

	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="users_id")
    private User users;
	
	@JsonIgnore
	@OneToOne(mappedBy = "transactions")
    private TransactionDetails transaction_details;
	
//	public Transactions(int insurancePrice, int shippingPrice, int totalPrice, String transactionStatus, String code) {
//		this.insurancePrice = insurancePrice;
//		this.shippingPrice = shippingPrice;
//		this.totalPrice = totalPrice;
//		this.transactionStatus = transactionStatus;
//		this.code = code;
//	}
}

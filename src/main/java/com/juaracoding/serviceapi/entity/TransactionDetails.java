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
@Table(	name = "transaction_details")
public class TransactionDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int price;
	private int shippingStatus;
	private int resi;
	private String code;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transactions_id", referencedColumnName = "id")
    private Transactions transactions;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "products_id", referencedColumnName = "id")
    private Products products;
	
	public TransactionDetails(int price, int shippingStatus, int resi, String code) {
		this.price = price;
		this.shippingStatus = shippingStatus;
		this.resi = resi;
		this.code = code;
	}
}

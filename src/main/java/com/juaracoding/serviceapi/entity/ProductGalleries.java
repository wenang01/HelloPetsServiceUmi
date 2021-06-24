package com.juaracoding.serviceapi.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(	name = "product_galleries")
public class ProductGalleries {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	public String image;
	
//	@JsonIgnore
//	@ManyToOne
//    @JoinColumn(name="products_id", nullable=false)
//    private Products products;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="products_id", nullable=false)
    private Products products;
	
	

	
	public ProductGalleries() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductGalleries(Long id, String image, Products products) {
	super();
	this.id = id;
	this.image = image;
	this.products = products;
}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}


}

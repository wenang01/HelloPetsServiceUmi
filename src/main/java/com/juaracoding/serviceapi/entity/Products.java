package com.juaracoding.serviceapi.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(	name = "products")
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String productName;
	private double price;
	private int stok;
	private String productImage;
	private String description;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="categories_id", nullable=false)
    private Categories categories;
	

//	@JsonIgnore
//	@OneToMany(mappedBy="products")
//    private Set<ProductGalleries> productGalleries;
	@JsonIgnoreProperties("products")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "products", cascade = CascadeType.ALL)
    private Set<ProductGalleries> productGalleries = new HashSet<ProductGalleries>();
	
	@JsonIgnore
	@OneToOne(mappedBy = "products")
    private TransactionDetails transaction_details;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStok() {
		return stok;
	}

	public void setStok(int stok) {
		this.stok = stok;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public Set<ProductGalleries> getProductGalleries() {
		return productGalleries;
	}

	public void setProductGalleries(Set<ProductGalleries> productGalleries) {
		this.productGalleries = productGalleries;
	}

	public TransactionDetails getTransaction_details() {
		return transaction_details;
	}

	public void setTransaction_details(TransactionDetails transaction_details) {
		this.transaction_details = transaction_details;
	}

	public Products(String productName, double price, int stok, String productImage, String description,
			Categories categories) {
		super();
		this.productName = productName;
		this.price = price;
		this.stok = stok;
		this.productImage = productImage;
		this.description = description;
		this.categories = categories;
	}

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

}

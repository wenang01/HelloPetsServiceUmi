package com.juaracoding.serviceapi.services;

import java.util.List;

import com.juaracoding.serviceapi.entity.ProductGalleries;
import com.juaracoding.serviceapi.entity.Products;

public interface ModelProductGalleryInterface {

	List<ProductGalleries> getAllProductImages();
	
	public ProductGalleries addProductImages(ProductGalleries productImages);
	
	public List<ProductGalleries> addProductImagesList(List<ProductGalleries> productImages);
	
	public void deleteProductImage(String id);
	
	public ProductGalleries cariProductImage(String id);

//	List<ProductGalleries> getByProductsId(String products_id);
	
}

package com.kaiba.product_catalog.service;


import com.kaiba.product_catalog.dto.ProductRequest;
import com.kaiba.product_catalog.dto.ProductResponse;
import com.kaiba.product_catalog.entity.Product;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    List<ProductResponse> getProducts();
    ProductResponse getProduct(Long id);
    void deleteProduct(Long id);
    ProductResponse updateProduct(Long id, ProductRequest request);
}

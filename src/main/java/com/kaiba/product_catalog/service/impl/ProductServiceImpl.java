package com.kaiba.product_catalog.service.impl;


import com.kaiba.product_catalog.config.ModelMapperConfig;
import com.kaiba.product_catalog.dto.ProductRequest;
import com.kaiba.product_catalog.dto.ProductResponse;
import com.kaiba.product_catalog.entity.Product;
import com.kaiba.product_catalog.exceptions.ResourceNotFoundException;
import com.kaiba.product_catalog.repository.ProductRepository;
import com.kaiba.product_catalog.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = modelMapper.map(request, Product.class);
        Product saved = productRepository.save(product);
        return modelMapper.map(saved, ProductResponse.class);
    }

    @Override
    public List<ProductResponse> getProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(
                        product, ProductResponse.class
                )).toList();
    }

    @Override
    public ProductResponse getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return modelMapper.map(product, ProductResponse.class);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest updatedRequest) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        modelMapper.map(updatedRequest,existing);

       Product updated = productRepository.save(existing);
        return modelMapper.map(updated, ProductResponse.class);
    }
}

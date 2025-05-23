package com.kaiba.product_catalog.config;

import com.kaiba.product_catalog.entity.Product;
import com.kaiba.product_catalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository repository;

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            List<Product> products = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                Product product = new Product(
                        null,
                        "Producto " + i,
                        BigDecimal.valueOf(i * 10.0),
                        "Descripción del producto " + i
                );
                products.add(product);
            }
            repository.saveAll(products);
            System.out.println("✅ Se insertaron 100 productos en H2");
        }
    }
}

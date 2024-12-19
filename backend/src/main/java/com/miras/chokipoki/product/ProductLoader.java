package com.miras.chokipoki.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class ProductLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Autowired
    public ProductLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws IOException {
        String jsonData = new String(Files.readAllBytes(Paths.get("src/main/resources/data.json")));

        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> products = objectMapper.readValue(jsonData, objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class));

        productRepository.saveAll(products);

        System.out.println("Data loaded successfully.");
    }
}
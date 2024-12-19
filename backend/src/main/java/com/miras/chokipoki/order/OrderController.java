package com.miras.chokipoki.order;

import com.miras.chokipoki.product.Product;
import com.miras.chokipoki.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order orderRequest) {
        try {
            List<Product> orderedProducts = new ArrayList<>();
            double totalPrice = 0;
            int totalItems = 0;

            for (Product product : orderRequest.getProducts()) {
                Product productFromDB = productRepository.findById(product.getId()).orElseThrow();
                totalPrice += productFromDB.getPrice() * product.getQuantityInOrder();
                totalItems += product.getQuantityInOrder();
                orderedProducts.add(productFromDB);
            }

            Order order = new Order(orderedProducts, totalPrice, totalItems);


            Order savedOrder = orderRepository.save(order);

            System.out.println("Order created successfully: " + savedOrder);
            return ResponseEntity.ok(savedOrder);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            List<Order> orders = orderRepository.findAll();
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}

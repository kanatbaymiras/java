package com.miras.chokipoki.product;

import com.miras.chokipoki.order.Order;
import jakarta.persistence.*;

import java.util.List;


@Table(name="products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private long id;
    private String name;
    private double price;
    private String description;

    private Integer quantityInOrder;

    @ManyToMany
    private List<Order> orders;

    public Product() {

    }

    public Product(long id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantityInOrder = (quantityInOrder != null) ? quantityInOrder : 0;
    }

    public Product(long id, String name, double price, String description, Integer quantityInOrder) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantityInOrder = (quantityInOrder != null) ? quantityInOrder : 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantityInOrder() {
        return quantityInOrder;
    }

    public void setQuantityInOrder(int quantityInOrder) {
        this.quantityInOrder = quantityInOrder;
    }
}

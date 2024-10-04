package com.example.spring_crud.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "products") // This maps the entity to a database table named "products"
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false) // Makes the column non-nullable
    private String name;

    @Column(nullable = false) // Makes the column non-nullable
    private Double price;

    // Default constructor (optional, but recommended)
    public Product() {
    }

    // Parameterized constructor (optional)
    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    // Getters and Setters
    public UUID getId() {  // Changed return type to UUID
        return id;
    }

    // No need for setId method if using @GeneratedValue, but it's kept here for flexibility
    public void setId(UUID id) {  // Changed parameter type to UUID
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // Optional: Override toString() for better readability
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

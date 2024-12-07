package org.example;

import java.util.Date;

public class Medicine {
    private final int id;
    private String name;
    private String category;
    private double price;
    private int stockQuantity;
    private Date expiryDate;
    private String manufacturer;

    public Medicine(int id, String name, String category, double price, int stockQuantity, Date expiryDate, String manufacturer) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.expiryDate = expiryDate;
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void deductStock(int quantity) {
        if (stockQuantity >= quantity) {
            stockQuantity -= quantity;
        } else {
            throw new IllegalStateException("Not enough stock available.");
        }
    }

    public boolean isExpired() {
        return new Date().after(expiryDate);
    }

    public boolean isLowStock() {
        return stockQuantity < 10; // Threshold
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Category: " + category + ", Price: $" + price
                + ", Stock: " + stockQuantity + ", Expiry: " + expiryDate + ", Manufacturer: " + manufacturer);
    }
}

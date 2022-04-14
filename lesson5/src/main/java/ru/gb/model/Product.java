package ru.gb.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String title;
    @Column
    private int price;

    public Product() {
    }

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public int getCost() {
        return price;
    }

    public void setCost(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product: id=" + id + ", title='" + title + "', price='" + price + "';";
    }
}

package org.example.finalproject.domains;

import jakarta.persistence.*;

@Entity
@Table( name = "menu_items" )
public class MenuItem {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double price;

    public MenuItem() {
    }

    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

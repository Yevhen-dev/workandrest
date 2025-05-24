package org.example.finalproject.domains;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table( name = "orders" )
public class Order {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn( name = "user_id" )
    private User user;

    @OneToMany( mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<OrderItem> items;

    public Order() {
    }

    public Order(User user) {
        this.user = user;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}

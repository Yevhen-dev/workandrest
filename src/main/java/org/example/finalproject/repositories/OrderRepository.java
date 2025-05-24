package org.example.finalproject.repositories;

import org.example.finalproject.domains.Order;
import org.example.finalproject.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

}

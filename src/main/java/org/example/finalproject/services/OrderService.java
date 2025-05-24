package org.example.finalproject.services;

import org.example.finalproject.domains.MenuItem;
import org.example.finalproject.domains.Order;
import org.example.finalproject.domains.OrderItem;
import org.example.finalproject.domains.User;
import org.example.finalproject.repositories.MenuItemRepository;
import org.example.finalproject.repositories.OrderItemRepository;
import org.example.finalproject.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Transactional
    public void createOrder(User user, Map<Long, Integer> quantities) {

        Order order = new Order();
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> items = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : quantities.entrySet()) {
            Long itemId = entry.getKey();
            Integer quantity = entry.getValue();

            if (quantity <= 0) continue;

            MenuItem menuItem = menuItemRepository.findById(itemId)
                    .orElseThrow(() -> new RuntimeException("Menu item not found"));

            OrderItem item = new OrderItem();
            item.setMenuItem(menuItem);
            item.setQuantity(quantity);
            item.setOrder(order);

            items.add(item);

        }

        order.setItems(items);
        orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}

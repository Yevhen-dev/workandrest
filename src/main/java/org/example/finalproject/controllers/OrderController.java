package org.example.finalproject.controllers;

import org.example.finalproject.domains.Order;
import org.example.finalproject.domains.User;
import org.example.finalproject.repositories.MenuItemRepository;
import org.example.finalproject.repositories.OrderRepository;
import org.example.finalproject.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MenuItemRepository menuItemRepository;


    @GetMapping( "/menu" )
    public String menu(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("menuItems", menuItemRepository.findAll());
        return "menu";
    }


    @PostMapping("/send")
    public String sendOrder(@RequestParam(value = "menuItemIds", required = false) List<Long> menuItemIds,
                            @RequestParam Map<String, String> requestParams,
                            @AuthenticationPrincipal User user) {

        if (menuItemIds == null || menuItemIds.isEmpty()) {
            return "redirect:/menu?error=empty";
        }

        Map<Long, Integer> quantities = new HashMap<>();
        boolean hasOrder = false;

        for (Long id : menuItemIds) {
            String key = "quantities[" + id + "]";
            int qty = Integer.parseInt(requestParams.get(key));

            if (qty > 0) {
                hasOrder = true;
                quantities.put(id, qty);
            }
        }

        if (!hasOrder) {
            return "redirect:/menu?error=empty";
        }

        orderService.createOrder(user, quantities);
        return "redirect:/confirmOrder";
    }

    @GetMapping( "/confirmOrder" )
    public String confirmOrder(@AuthenticationPrincipal User user, Model model) {
        List<Order> orders = orderService.getOrdersByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        return "/confirmOrder";
    }

    @GetMapping("/allOrders")
    public String viewAllOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        orders.sort(Comparator.comparing(Order::getCreatedAt).reversed());
        model.addAttribute("orders", orders);
        return "allOrders";
    }



}

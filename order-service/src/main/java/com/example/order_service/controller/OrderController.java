package com.example.order_service.controller;

import com.example.order_service.model.Order;
import com.example.order_service.service.OrderService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place-order")
    public ResponseEntity<?> placeOrder(@RequestParam Long productId, @RequestParam Integer quantity)
    {
        Order order = orderService.placeOrder(productId, quantity);
        String productName = orderService.getProductName(order.getProductId());

        Map<String, Object> response = new HashMap<>();
        response.put("OrderId", order.getId());
        response.put("Product id:",order.getProductId());
        response.put("ProductName", productName);
        response.put("Quantity", order.getQuantity());
        response.put("TotalPrice", order.getTotalPrice());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id)
    {
        try
        {
            Order order = orderService.fetchOrders(id);
            String productName = orderService.getProductName(order.getProductId());

            Map<String, Object> response = new HashMap<>();
            response.put("OrderId", order.getId());
            response.put("ProductId:",order.getProductId());
            response.put("ProductName", productName);
            response.put("Quantity", order.getQuantity());
            response.put("TotalPrice", order.getTotalPrice());

            return ResponseEntity.ok(response);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

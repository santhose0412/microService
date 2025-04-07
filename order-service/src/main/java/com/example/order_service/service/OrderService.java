package com.example.order_service.service;

import com.example.order_service.client.ProductClient;
import com.example.order_service.dto.ProductResponse;
import com.example.order_service.model.Order;
import com.example.order_service.repository.OrderRepo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderRepo orderRepo;;

    public Order placeOrder(Long id, Integer quantity)
    {
        ProductResponse product = productClient.getProductById(id);

        Double totalPrice = product.getPrice() * quantity;

        Order order = new Order();
        order.setProductId(id);
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice);

        return orderRepo.save(order);
    }

    public String getProductName(Long productId)
    {
        return productClient.getProductById(productId).getName();
    }

    public Order fetchOrders(Long id)
    {
        return orderRepo.findById(id).orElseThrow( () -> new RuntimeException("Order with id "+id+" not found!..."));
    }
}

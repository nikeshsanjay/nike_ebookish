package com.example.backend.service;

import com.example.backend.model.Orders;
import com.example.backend.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    public Orders create(Orders order) {
        if (order != null) {
            return orderRepo.save(order);
        } else {
            throw new IllegalArgumentException("Order object cannot be null");
        }
    }

    public List<Orders> getAllOrders() {
        return orderRepo.findAll();
    }

    public Orders getOrderById(int id) {
        return orderRepo.findById(id).orElse(null);
    }

    public boolean updateOrder(int id, Orders order) {
        if (getOrderById(id) == null)
            return false;

        if (order != null) {
            order.setId(id); // Ensure the ID is set for the correct entity
            orderRepo.save(order);
        } else {
            throw new IllegalArgumentException("No order with the given ID found");
        }

        return true;
    }

    public boolean deleteOrder(int id) {
        if (getOrderById(id) == null)
            return false;

        orderRepo.deleteById(id);

        return true;
    }
}

package com.epam.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.library.domain.User;
import com.epam.library.domain.order.Order;
import com.epam.library.domain.order.OrderStatus;
import com.epam.library.repository.order.OrderRepository;

@Service
public class OrderService {
    private OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.repository = orderRepository;
    }

    @Transactional
    public Page<Order> getPagedOrdersOfType(OrderStatus orderStatus, Integer pageNumber,
            Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.ASC,
                "orderDate");
        return repository.findByOrderStatus(orderStatus, request);
    }

    @Transactional
    public Page<Order> getPagedOrdersOfUser(User user, Integer pageNumber,
            Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.DESC,
                "orderDate");
        return repository.findByUser(user, request);
    }
    
    @Transactional
    public Page<Order> getPagedOrders(Integer pageNumber, Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.ASC,
                "orderDate");
        return repository.findAll(request);
    }
    
    @Transactional
    public Page<Order> searchPagedOrders(String login, Integer pageNumber, Integer pageSize) {
        PageRequest request = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.ASC,
                "orderDate");
        return repository.findByUser_LoginData_LoginContaining(login, request);
    }

    public Order addOrder(Order order) {
        return repository.save(order);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    // @Transactional
    // public List<Order> findAllEager() {
    // List<Order> orders =repository.findAll();
    // for(Order order:orders) {
    // order.
    // }
    // return orders;
    // }

    public Order createOrder(Order order) {
        return repository.save(order);
    }

    public List<Order> findAllWithConcreteStatus(OrderStatus orderStatus) {
        return repository.findByOrderStatus(orderStatus);
    }

    public Order updateOrder(Order order) {
        return repository.save(order);
    }

    public Order findOrderById(Long orderId) {
        return repository.findOne(orderId);
    }
}

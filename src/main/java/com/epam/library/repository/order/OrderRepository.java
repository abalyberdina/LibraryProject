package com.epam.library.repository.order;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.library.domain.User;
import com.epam.library.domain.book.Book;
import com.epam.library.domain.order.Order;
import com.epam.library.domain.order.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByBook(Book book);

    List<Order> findByOrderDate(Date orderDate);

    List<Order> findByDeadlineDate(Date deadline);

    List<Order> findByDeliverDate(Date delivered);

    List<Order> findByOrderStatus(OrderStatus orderStatus);

    Page<Order> findByOrderStatus(OrderStatus orderStatus, Pageable pageable);

    Page<Order> findByUser(User user, Pageable pageable);

    Page<Order> findByUser_LoginData_LoginContaining(String login, Pageable pageable);

    List<Order> findByOrderDateBetween(Date startDate, Date endDate);

    List<Order> findByDeliverDateBetween(Date startDate, Date endDate);

    List<Order> findByDeadlineDateBetween(Date startDate, Date endDate);

    List<Order> findByUser(User user);

    List<Order> findByBook(Book book, Sort sort);

    List<Order> findByOrderDate(Date orderDate, Sort sort);

    List<Order> findByDeadlineDate(Date deadline, Sort sort);

    List<Order> findByDeliverDate(Date delivered, Sort sort);

    List<Order> findByOrderStatus(OrderStatus orderStatus, Sort sort);

    List<Order> findByOrderDateBetween(Date startDate, Date endDate, Sort sort);

    List<Order> findByDeliverDateBetween(Date startDate, Date endDate, Sort sort);

    List<Order> findByDeadlineDateBetween(Date startDate, Date endDate, Sort sort);

    List<Order> findByUser(User user, Sort sort);
}

package com.epam.library.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.domain.Page;

import com.epam.library.domain.order.Order;

public class PersonalOrderView {

    private static final DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Long orderID;
    private String orderStatus;
    private Long bookID;
    private String bookName;
    private String orderDate;
    private String dateToReturn;
    private String dateReturned;

    public PersonalOrderView(Order order) {
        this.orderID = order.getId();
        this.orderStatus = order.getOrderStatus().name();
        this.bookID = order.getBook().getId();
        this.bookName = order.getBook().getBookName();
        this.orderDate = FORMATTER.format(order.getOrderDate());
        this.dateToReturn = order.getDeadlineDate() == null ? " " : FORMATTER.format(order
                .getDeadlineDate());
        this.dateReturned = order.getDeliverDate() == null ? " " : FORMATTER.format(order
                .getDeliverDate());
    }

    public static Collection<PersonalOrderView> of(Page<Order> all) {
        Collection<PersonalOrderView> views = new ArrayList<>(all.getSize());
        for (Order order : all) {
            views.add(new PersonalOrderView(order));
        }
        return views;
    }

    public Long getOrderID() {
        return orderID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Long getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getDateToReturn() {
        return dateToReturn;
    }

    public String getDateReturned() {
        return dateReturned;
    }
}

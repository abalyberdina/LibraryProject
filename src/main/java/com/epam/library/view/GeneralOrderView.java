package com.epam.library.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.domain.Page;

import com.epam.library.domain.order.Order;

public class GeneralOrderView {

    private static final DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Long orderID;
    private String orderStatus;
    private String userName;
    private String userEmail;
    private String userPhone;
    private Long bookID;
    private String bookName;
    private Integer bookCount;
    private String orderDate;
    private String dateToReturn;
    private String dateReturned;

    public GeneralOrderView(Order order) {
        this.orderID = order.getId();
        this.orderStatus = order.getOrderStatus().name();
        this.userName = order.getUser().getFirstName() + " " + order.getUser().getLastName();
        this.bookID = order.getBook().getId();
        this.bookName = order.getBook().getBookName();
        this.bookCount = order.getBook().getNumber();
        this.orderDate = FORMATTER.format(order.getOrderDate());
        this.dateToReturn = order.getDeadlineDate() == null ? " " : FORMATTER.format(order
                .getDeadlineDate());
        this.dateReturned = order.getDeliverDate() == null ? " " : FORMATTER.format(order
                .getDeliverDate());
        this.userEmail = order.getUser().getLoginData().getLogin();
        this.userPhone = order.getUser().getContactPhone() == null ? " " : order.getUser()
                .getContactPhone();
    }

    public static Collection<GeneralOrderView> of(Page<Order> all) {
        Collection<GeneralOrderView> views = new ArrayList<>(all.getSize());
        for (Order order : all) {
            views.add(new GeneralOrderView(order));
        }
        return views;
    }

    public Long getOrderID() {
        return orderID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getUserName() {
        return userName;
    }

    public Long getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public Integer getBookCount() {
        return bookCount;
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

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }
}

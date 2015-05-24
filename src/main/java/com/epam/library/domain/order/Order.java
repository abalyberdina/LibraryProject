package com.epam.library.domain.order;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;

import com.epam.library.domain.User;
import com.epam.library.domain.book.Book;

@Entity
@Table(name = "CLIENT_ORDER")
public class Order {
    @Transient
    private static int NUM_OF_DAYS_TO_KEEP_BOOK = 10;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ")
    @SequenceGenerator(name = "ORDER_SEQ", sequenceName = "ORDER_SEQ", allocationSize = 1)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ORDER_DATE", nullable = false, updatable = false)
    @CreatedDate
    private Date orderDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DEADLINE")
    private Date deadlineDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DELIVER_DATE")
    private Date deliverDate;

    @Column(name = "ORDER_STATUS", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "ID_USER", nullable = false)
    private User user;

    // @ManyToMany
    // @JoinTable(name = "ORDER_ITEM", joinColumns = { @JoinColumn(name =
    // "ID_ORDER", nullable = false) },
    // inverseJoinColumns = { @JoinColumn(name = "ID_BOOK", nullable = false) })
    // private List<Book> books;

    @ManyToOne
    @JoinColumn(name = "ID_BOOK", nullable = false)
    private Book book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public static int getNUM_OF_DAYS_TO_KEEP_BOOK() {
        return NUM_OF_DAYS_TO_KEEP_BOOK;
    }

}

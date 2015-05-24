package com.epam.library.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.HEAD;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.library.domain.User;
import com.epam.library.domain.book.Book;
import com.epam.library.domain.order.Order;
import com.epam.library.domain.order.OrderStatus;
import com.epam.library.service.BookService;
import com.epam.library.service.OrderService;
import com.epam.library.service.UserService;
import com.epam.library.support.SecurityUserDetails;
import com.epam.library.view.GeneralOrderView;
import com.epam.library.view.OrderView;

@Controller
public class OrderController {
    private OrderService orderService;
    private BookService bookService;
    private UserService userService;

    @Autowired
    public OrderController(OrderService orderServ, BookService bookServ, UserService userServ) {
        this.orderService = orderServ;
        this.bookService = bookServ;
        this.userService = userServ;
    }

    @RequestMapping(value = "/books/order/{bookId}", method = { GET, HEAD })
    public String orderBook(@PathVariable("bookId") Long bookId, ModelMap model,
            @AuthenticationPrincipal SecurityUserDetails sud) {
        Book book = bookService.findBookByIDEager(bookId);
        User user = userService.findUserByLogin(sud.getUsername());
        OrderView orderView = new OrderView(user.getId(), book);
        model.put("order", orderView);
        return "/orders/order";
    }

    @RequestMapping(value = "/books/order/{bookId}", method = POST)
    public String doAddOrder(HttpServletRequest request, @PathVariable("bookId") Long bookId,
            @AuthenticationPrincipal SecurityUserDetails sud) {
        Order order = new Order();
        order.setBook(bookService.findBookByIDLazy(bookId));
        order.setUser(userService.findUserByLogin(sud.getUsername()));
        order.setOrderDate(new Date());
        order.setOrderStatus(OrderStatus.NEWORDER);
        orderService.createOrder(order);
        request.getSession().setAttribute("successAction", "success.order.add");

        return "redirect:/books/all";
    }

    @RequestMapping(value = "orders/all", method = { GET, HEAD })
    public String showAllOrders(ModelMap model,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "20") Integer pagesize,
            @RequestParam(value = "query", defaultValue = "") String login) {
        pagesize = checkPageSize(pagesize);
        Page<Order> page = orderService.searchPagedOrders(login.trim(), pageNumber, pagesize);
        Collection<GeneralOrderView> views = GeneralOrderView.of(page);
        formPageBoundaries(model, pagesize, page);
        model.put("orders", views);
        model.addAttribute("query", login);
        return "/orders/allorders";
    }

    @RequestMapping(value = "orders/new", method = { GET, HEAD })
    public String showNewOrders(ModelMap model,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "20") Integer pagesize) {
        pagesize = checkPageSize(pagesize);
        Page<Order> page = orderService.getPagedOrdersOfType(OrderStatus.NEWORDER, pageNumber,
                pagesize);
        Collection<GeneralOrderView> views = GeneralOrderView.of(page);
        formPageBoundaries(model, pagesize, page);
        model.put("orders", views);
        return "/orders/neworders";
    }

    @RequestMapping(value = "orders/active", method = { GET, HEAD })
    public String showActiveOrders(ModelMap model,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "20") Integer pagesize) {
        pagesize = checkPageSize(pagesize);
        Page<Order> page = orderService.getPagedOrdersOfType(OrderStatus.INPROGRESS, pageNumber,
                pagesize);
        Collection<GeneralOrderView> views = GeneralOrderView.of(page);
        formPageBoundaries(model, pagesize, page);
        model.put("orders", views);
        return "/orders/ordersinprogress";
    }

    @RequestMapping(value = "orders/canceled", method = { GET, HEAD })
    public String showCanceledOrders(ModelMap model,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "20") Integer pagesize) {
        pagesize = checkPageSize(pagesize);
        Page<Order> page = orderService.getPagedOrdersOfType(OrderStatus.REJECTED, pageNumber,
                pagesize);
        Collection<GeneralOrderView> views = GeneralOrderView.of(page);
        formPageBoundaries(model, pagesize, page);
        model.put("orders", views);
        return "/orders/canceledorders";
    }

    @RequestMapping(value = "orders/closed", method = { GET, HEAD })
    public String showClosedOrders(ModelMap model,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "20") Integer pagesize) {
        pagesize = checkPageSize(pagesize);
        Page<Order> page = orderService.getPagedOrdersOfType(OrderStatus.SUCCESSFULL, pageNumber,
                pagesize);
        Collection<GeneralOrderView> views = GeneralOrderView.of(page);
        formPageBoundaries(model, pagesize, page);
        model.put("orders", views);
        return "/orders/closedorders";
    }

    @RequestMapping(value = "/orders/confirm/{orderId}", method = { GET, HEAD })
    public String confirmOrder(HttpServletRequest request, @PathVariable("orderId") Long orderId){
        Order order = orderService.findOrderById(orderId);
        if (order.getBook().getNumber() < 1) {
            request.getSession().setAttribute("dangerError", "error.order.confirm");
            return "redirect:/orders/new";
        }
        order.setOrderStatus(OrderStatus.INPROGRESS);
        Book book = order.getBook();
        book.setNumber(book.getNumber() - 1);
        bookService.updateBook(book);
        order.setDeadlineDate(new Date(new Date().getTime()
                + TimeUnit.DAYS.toMillis(Order.getNUM_OF_DAYS_TO_KEEP_BOOK())));
        orderService.updateOrder(order);
        return "redirect:/orders/active";
    }

    @RequestMapping(value = "/orders/cancel/{orderId}", method = { GET, HEAD })
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        Order order = orderService.findOrderById(orderId);
        order.setOrderStatus(OrderStatus.REJECTED);
        orderService.updateOrder(order);
        return "redirect:/orders/canceled";
    }

    @RequestMapping(value = "/orders/close/{orderId}", method = { GET, HEAD })
    public String closeOrder(@PathVariable("orderId") Long orderId) {
        Order order = orderService.findOrderById(orderId);
        order.setOrderStatus(OrderStatus.SUCCESSFULL);
        Book book = order.getBook();
        book.setNumber(book.getNumber() + 1);
        bookService.updateBook(book);
        order.setDeliverDate(new Date());
        orderService.updateOrder(order);
        return "redirect:/orders/closed";
    }

    @RequestMapping(value = "myprofile/myorders", method = { GET, HEAD })
    public String showPersonalOrders(ModelMap model,
            @AuthenticationPrincipal SecurityUserDetails sud,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "20") Integer pagesize) {
        pagesize = checkPageSize(pagesize);
        Page<Order> page = orderService.getPagedOrdersOfUser(
                userService.findUserByLogin(sud.getUsername()), pageNumber, pagesize);
        Collection<GeneralOrderView> views = GeneralOrderView.of(page);
        formPageBoundaries(model, pagesize, page);
        model.put("orders", views);
        return "/orders/personalorders";
    }

    private Integer checkPageSize(Integer pagesize) {
        if (pagesize > 50) {
            pagesize = 50;
        }
        if (pagesize <= 0) {
            pagesize = 20;
        }
        return pagesize;
    }

    private void formPageBoundaries(ModelMap model, Integer pagesize, Page<Order> page) {
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("pageTotal", page.getTotalPages());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("size", pagesize);
    }
}

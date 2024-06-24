package org.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.dao.OrderDao;
import org.web.dto.Result;
import org.web.entity.*;
import org.web.util.JwtUtil;

import java.util.*;

@Service
public class OrderService {
    @Autowired
    private JwtUtil jwtUtil;
    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Result createOrder(Orders orders){
        orderDao.save(orders);
        return new Result(200, "success");
    }
    public Result updateOder(Orders orders){
        orderDao.save(orders);
        return new Result(200, "success");
    }
    public Result deleteOrder(Integer id){
        orderDao.deleteById(id);
        return new Result(200, "success");
    }
    public Result getOrder(Integer id){
        Optional<Orders> optionalOrders = orderDao.findById(id);
        if(optionalOrders.isPresent()){
            return new Result(200, optionalOrders.get());
        }else{
            return new Result(9999, "no data");
        }
    }
//    public List<Map<String, Object>> getOrdersAndPaymentByToken(String token) {
//        Integer userId = jwtUtil.getUserIdFromToken(token);
//        List<Object[]> results = orderDao.findOrdersAndPaymentByUserId(userId);
//
//        List<Map<String, Object>> orderPaymentList = new ArrayList<>();
//        for (Object[] result : results) {
//            Map<String, Object> orderPaymentMap = new HashMap<>();
//            Orders order = (Orders) result[0];
//            Payment payment = (Payment) result[1];
//            orderPaymentMap.put("order", order);
//            orderPaymentMap.put("payment", payment);
//            orderPaymentList.add(orderPaymentMap);
//        }
//        return orderPaymentList;
//    }
public List<Map<String, Object>> getOrdersPaymentsTicketsShowtimeMovieScreenAndTheaterByToken(String token) {
    Integer userId = jwtUtil.getUserIdFromToken(token);
    List<Object[]> results = orderDao.findOrdersWithPaymentsTicketsShowtimeMovieScreenAndTheaterByUserId(userId);

    List<Map<String, Object>> orderPaymentTicketShowtimeMovieScreenTheaterList = new ArrayList<>();
    for (Object[] result : results) {
        // Logging the result length and contents for debugging
        System.out.println("Result length: " + result.length);
        for (Object obj : result) {
            System.out.println(obj);
        }

        Map<String, Object> orderPaymentTicketShowtimeMovieScreenTheaterMap = new HashMap<>();

        Orders order = (Orders) result[0];
        Payment payment = (Payment) result[1];
        Tickets ticket = (Tickets) result[2];
        Showtime showtime = (Showtime) result[3];
        Movie movie = (Movie) result[4];
        Screen screen = (Screen) result[5];
        Theater theater = result.length > 6 && result[6] instanceof Theater ? (Theater) result[6] : null;

        orderPaymentTicketShowtimeMovieScreenTheaterMap.put("order", order);
        orderPaymentTicketShowtimeMovieScreenTheaterMap.put("payment", payment);
        orderPaymentTicketShowtimeMovieScreenTheaterMap.put("ticket", ticket);
        orderPaymentTicketShowtimeMovieScreenTheaterMap.put("showtime", showtime);
        orderPaymentTicketShowtimeMovieScreenTheaterMap.put("movie", movie);
        orderPaymentTicketShowtimeMovieScreenTheaterMap.put("screen", screen);
        orderPaymentTicketShowtimeMovieScreenTheaterMap.put("theater", theater);

        orderPaymentTicketShowtimeMovieScreenTheaterList.add(orderPaymentTicketShowtimeMovieScreenTheaterMap);
    }
    return orderPaymentTicketShowtimeMovieScreenTheaterList;
}
}

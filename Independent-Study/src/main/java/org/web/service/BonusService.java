package org.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.dao.BonusDao;
import org.web.util.JwtUtil;
import org.web.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BonusService {

    @Autowired
    private BonusDao bonusDao;

    @Autowired
    private JwtUtil jwtUtil;

    public List<Map<String, Object>> getBonusByToken(String token) {
        Integer userId = jwtUtil.getUserIdFromToken(token);
        List<Object[]> results = bonusDao.findByAll(userId);

        List<Map<String, Object>> orderPaymentTicketShowtimeMovieScreenTheaterBonusList = new ArrayList<>();
        for (Object[] result : results) {
            Map<String, Object> orderPaymentTicketShowtimeMovieScreenTheaterBonusMap = new HashMap<>();

            Orders order = (Orders) result[0];
            Payment payment = (Payment) result[1];
            Tickets ticket = (Tickets) result[2];
            Showtime showtime = (Showtime) result[3];
            Movie movie = (Movie) result[4];
            Screen screen = (Screen) result[5];
            Theater theater = result.length > 6 && result[6] instanceof Theater ? (Theater) result[6] : null;
            Bonus bonus = result.length > 7 && result[7] instanceof Bonus ? (Bonus) result[7] : null;

            orderPaymentTicketShowtimeMovieScreenTheaterBonusMap.put("order", order);
            orderPaymentTicketShowtimeMovieScreenTheaterBonusMap.put("payment", payment);
            orderPaymentTicketShowtimeMovieScreenTheaterBonusMap.put("ticket", ticket);
            orderPaymentTicketShowtimeMovieScreenTheaterBonusMap.put("showtime", showtime);
            orderPaymentTicketShowtimeMovieScreenTheaterBonusMap.put("movie", movie);
            orderPaymentTicketShowtimeMovieScreenTheaterBonusMap.put("screen", screen);
            orderPaymentTicketShowtimeMovieScreenTheaterBonusMap.put("theater", theater);
            orderPaymentTicketShowtimeMovieScreenTheaterBonusMap.put("bonus", bonus);

            orderPaymentTicketShowtimeMovieScreenTheaterBonusList.add(orderPaymentTicketShowtimeMovieScreenTheaterBonusMap);
        }
        return orderPaymentTicketShowtimeMovieScreenTheaterBonusList;
    }
}
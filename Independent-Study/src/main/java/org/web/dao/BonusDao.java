package org.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.web.entity.Bonus;

import java.util.List;

@Repository
public interface BonusDao extends JpaRepository<Bonus, Integer> {

    @Query("SELECT o, p, t, s, m, sc, th, b FROM Orders o " +
            "LEFT JOIN Payment p ON o.id = p.ordersId " +
            "LEFT JOIN Tickets t ON o.id = t.ordersId " +
            "LEFT JOIN Showtime s ON t.showtimeId = s.id " +
            "LEFT JOIN Movie m ON s.movieId = m.id " +
            "LEFT JOIN Screen sc ON s.screenId = sc.id " +
            "LEFT JOIN Theater th ON sc.theaterId = th.id " +
            "LEFT JOIN Bonus b ON p.id = b.paymentId " +
            "WHERE o.userId = :userId")
    List<Object[]> findByAll(@Param("userId") int userId);
}
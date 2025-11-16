package hr.java.flightreservationsystem.entity;

import java.math.BigDecimal;

public record SeatPrice(SeatClass seatClass, BigDecimal price) {
}

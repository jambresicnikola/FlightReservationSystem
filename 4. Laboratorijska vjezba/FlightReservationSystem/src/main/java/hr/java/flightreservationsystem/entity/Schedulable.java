package hr.java.flightreservationsystem.entity;

import java.time.LocalDate;

public interface Schedulable {
    void schedule(LocalDate scheduledDate);
}

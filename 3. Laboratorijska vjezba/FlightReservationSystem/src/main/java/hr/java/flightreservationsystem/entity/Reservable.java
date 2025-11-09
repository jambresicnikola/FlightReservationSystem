package hr.java.flightreservationsystem.entity;

public sealed interface Reservable permits Booking {
    void reserve();
    void cancel();
}

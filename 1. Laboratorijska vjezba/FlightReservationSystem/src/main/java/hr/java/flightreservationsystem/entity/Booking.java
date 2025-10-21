package hr.java.flightreservationsystem.entity;

public class Booking {
    private User user;
    private Flight flight;
    private Integer seatNumber;

    public Booking(User user, Flight flight, Integer seatNumber) {
        this.user = user;
        this.flight = flight;
        this.seatNumber = seatNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }
}

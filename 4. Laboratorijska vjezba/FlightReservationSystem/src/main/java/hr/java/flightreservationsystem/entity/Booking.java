package hr.java.flightreservationsystem.entity;

public non-sealed class Booking implements Reservable {
    private Passenger passenger;
    private Flight flight;
    private Integer seatNumber;
    private Boolean seatReserved;
    private SeatPrice seatPrice;

    private Booking (BookingBuilder builder) {
        this.passenger = builder.passenger;
        this.flight = builder.flight;
        this.seatNumber = builder.seatNumber;
        this.seatReserved = builder.seatReserved;
        this.seatPrice = builder.seatPrice;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
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

    public Boolean getSeatReserved() {
        return seatReserved;
    }

    public void setSeatReserved(Boolean seatReserved) {
        this.seatReserved = seatReserved;
    }

    public SeatPrice getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(SeatPrice seatPrice) {
        this.seatPrice = seatPrice;
    }

    @Override
    public void reserve() {
        seatReserved = true;
        System.out.println("Seat reserved.");
    }

    @Override
    public void cancel() {
        seatReserved = false;
        System.out.println("Seat cancelled.");
    }

    public static class BookingBuilder {
        private final Passenger passenger;
        private final Flight flight;
        private final Integer seatNumber;
        private final SeatPrice seatPrice;

        private Boolean seatReserved = false;

        public BookingBuilder(Passenger passenger, Flight flight, Integer seatNumber, SeatPrice seatPrice) {
            this.passenger = passenger;
            this.flight = flight;
            this.seatNumber = seatNumber;
            this.seatPrice = seatPrice;
        }

        public BookingBuilder isSeatReserved(Boolean seatReserved) {
            this.seatReserved = seatReserved;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}

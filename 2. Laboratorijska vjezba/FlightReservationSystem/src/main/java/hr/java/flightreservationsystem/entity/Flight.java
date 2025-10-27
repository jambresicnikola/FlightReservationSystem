package hr.java.flightreservationsystem.entity;

import java.time.LocalDate;

public class Flight implements Schedulable {
    private Integer flightNumber;
    private String departure;
    private String destination;
    private LocalDate date;
    private Airline airline;
    private Pilot pilot;

    private Flight (FlightBuilder flightBuilder) {
        this.flightNumber = flightBuilder.flightNumber;
        this.departure = flightBuilder.departure;
        this.destination = flightBuilder.destination;
        this.date = flightBuilder.date;
        this.airline = flightBuilder.airline;
        this.pilot = flightBuilder.pilot;
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public static class FlightBuilder {
        private final Integer flightNumber;
        private final String departure;
        private final String destination;
        private final Airline airline;
        private final Pilot pilot;

        private LocalDate date =  LocalDate.now();

        public FlightBuilder(Integer flightNumber, String departure, String destination, Airline airline,  Pilot pilot) {
            this.flightNumber = flightNumber;
            this.departure = departure;
            this.destination = destination;
            this.airline = airline;
            this.pilot = pilot;
        }

        public FlightBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Flight build() {
            return new Flight(this);
        }
    }

    @Override
    public void schedule(LocalDate scheduledDate) {
        date = scheduledDate;
        System.out.println("Flight scheduled.");
    }
}

package hr.java.flightreservationsystem.util;

import hr.java.flightreservationsystem.entity.Airline;
import hr.java.flightreservationsystem.entity.Booking;
import hr.java.flightreservationsystem.entity.Flight;
import hr.java.flightreservationsystem.entity.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InputData {
    private InputData() {}

    public static User enterUserInfo(Scanner sc) {
        System.out.println("Enter Personal info:");
        System.out.println("First Name:");
        String  firstName = sc.nextLine();
        System.out.println("Last Name:");
        String lastName = sc.nextLine();
        System.out.println("Email:");
        String email = sc.nextLine();

        return new User(firstName, lastName, email);
    }

    public static Airline enterAirlineInfo(Scanner sc) {
        System.out.println("Enter Airline info:");
        System.out.println("Airline name:");
        String name = sc.nextLine();
        System.out.println("Airline code:");
        String code = sc.nextLine();

        return new Airline(name, code);
    }

    public static Flight enterFlightInfo(Scanner sc, Airline airline) {
        System.out.println("Enter Flight info:");
        System.out.println("Flight number:");
        Integer flightNumber = sc.nextInt();
        sc.nextLine();
        System.out.println("Departure location:");
        String departureLocation = sc.nextLine();
        System.out.println("Destination:");
        String destination = sc.nextLine();
        System.out.println("Flight date [dd.MM.yyyy.]:");
        String flightDate = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        LocalDate date =  LocalDate.parse(flightDate, formatter);

        return new Flight(flightNumber, departureLocation, destination, date, airline);
    }

    public static Booking enterBookingInfo(Scanner sc, User user, Flight flight) {
        Integer seatNumber;

        System.out.println("Enter booking info:");
        do {
            System.out.println("Seat number [1-25]:");
            seatNumber = sc.nextInt();
        } while (seatNumber < 1 || seatNumber > 25);

        return new Booking(user, flight, seatNumber);
    }
}

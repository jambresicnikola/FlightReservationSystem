package hr.java.flightreservationsystem.app;

import hr.java.flightreservationsystem.entity.Airline;
import hr.java.flightreservationsystem.entity.Booking;
import hr.java.flightreservationsystem.entity.Flight;
import hr.java.flightreservationsystem.entity.User;
import hr.java.flightreservationsystem.util.InputData;

import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final Integer NUMBER_OF_INPUTS = 5;

    public static void main(String[] args) {
        System.out.println("Welcome to Flight Reservation System!");

        Scanner sc = new Scanner(System.in);

        System.out.println("\nWould you like to enter your flight info [yes/no]?");
        String userChoice = sc.nextLine();

        User[] users = new User[NUMBER_OF_INPUTS];
        Airline[] airlines = new Airline[NUMBER_OF_INPUTS];
        Flight[] flights = new Flight[NUMBER_OF_INPUTS];
        Booking[] bookings = new Booking[NUMBER_OF_INPUTS];
        Integer i = 0;

        /*users[0] = new User("Nikola", "Nikica", "nikola@gmail.com");
        airlines[0] = new Airline("Airline 1", "12345");
        LocalDate date0 = LocalDate.of(2026, 10, 10);
        flights[0] = new Flight(987, "Prague", "Barcelona", date0, airlines[0]);
        bookings[0] = new Booking(users[0], flights[0], 1);

        users[1] = new User("Lei", "Goat", "lei@gmail.com");
        airlines[1] = new Airline("Airline 2", "23456");
        LocalDate date1 = LocalDate.of(2027, 10, 10);
        flights[1] = new Flight(876, "Osijek", "New York", date1, airlines[1]);
        bookings[1] = new Booking(users[1], flights[1], 2);

        users[2] = new User("Matei", "Mateo", "matei@gmail.com");
        airlines[2] = new Airline("Airline 3", "34567");
        LocalDate date2 = LocalDate.of(2028, 10, 10);
        flights[2] = new Flight(765, "Rijeka", "London", date2, airlines[2]);
        bookings[2] = new Booking(users[2], flights[2], 3);

        users[3] = new User("Luka", "Neandertalec", "luka@gmail.com");
        airlines[3] = new Airline("Airline 4", "45678");
        LocalDate date3 = LocalDate.of(2029, 10, 10);
        flights[3] = new Flight(654, "Split", "Rio de Janeiro", date3, airlines[3]);
        bookings[3] = new Booking(users[3], flights[3], 4);

        users[4] = new User("Matija", "Matko", "matija@gmail.com");
        airlines[4] = new Airline("Airline 5", "56789");
        LocalDate date4 = LocalDate.of(2030, 10, 10);
        flights[4] = new Flight(543, "Dubai", "Varaždin", date4, airlines[4]);
        bookings[4] = new Booking(users[4], flights[4], 5);*/

        while ("yes".equalsIgnoreCase(userChoice) && i < NUMBER_OF_INPUTS) {
            User user = InputData.enterUserInfo(sc);
            users[i] = user;

            Airline airline = InputData.enterAirlineInfo(sc);
            airlines[i] = airline;

            Flight flight = InputData.enterFlightInfo(sc, airline);
            flights[i] = flight;

            Booking booking = InputData.enterBookingInfo(sc, user, flight);
            sc.nextLine();
            bookings[i] = booking;

            i++;

            if (i < NUMBER_OF_INPUTS) {
                System.out.println("Would you like to continue [yes/no]?");
                userChoice = sc.nextLine();
            }
        }

        System.out.println("\nWould you like to see bookings data [yes/no]?");
        userChoice = sc.nextLine();

        while ("yes".equalsIgnoreCase(userChoice)) {
            System.out.println("Please choose flight number:");
            for (Booking booking : bookings) {
                System.out.println("- " + booking.getFlight().getFlightNumber());
            }


            Boolean validBooking = false;
            Booking selectedBooking = null;
            do {
                Integer selectedFlight = sc.nextInt();

                for (Booking booking : bookings) {
                    if (Objects.equals(booking.getFlight().getFlightNumber(), selectedFlight)) {
                        validBooking = true;
                        selectedBooking = booking;
                        break;
                    }
                }

                if (Boolean.FALSE.equals(validBooking)) {
                    System.out.println("Please enter a valid flight number.");
                }
            } while (Boolean.FALSE.equals(validBooking));

            printBookingData(selectedBooking);

            sc.nextLine();
            System.out.println("\nWould you like to continue [yes/no]?");
            userChoice = sc.nextLine();
        }

        System.out.println("\n-----------------------------------------------\n");
        System.out.println("Flight with minimal flight number: " + findMinFlightNumber(flights).getFlightNumber());
        System.out.println("Flight with maximal flight number: " + findMaxFlightNumber(flights).getFlightNumber());

        System.out.println("\nThank you for using our Flight Reservation System!");
    }

    private static void printBookingData(Booking booking) {
        System.out.println("\n-----------------------------------------------");

        System.out.println("\nUser info:"
                + "\nFull name: " + booking.getUser().getFirstName() + " " +  booking.getUser().getLastName()
                + "\nEmail: " +  booking.getUser().getEmail());

        System.out.println("\nFlight info:"
                + "\nFlight number: " + booking.getFlight().getFlightNumber()
                + "\nDeparture location: " + booking.getFlight().getDeparture()
                + "\nDestination: " + booking.getFlight().getDestination()
                + "\nDate: " + booking.getFlight().getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy.")));

        System.out.println("\nAirline info:"
                + "\nName: " + booking.getFlight().getAirline().getName()
                + "\nCode: " +  booking.getFlight().getAirline().getCode());

        System.out.println("\nSeat number: " + booking.getSeatNumber());

        System.out.println("\n-----------------------------------------------");
    }

    private static Flight findMinFlightNumber(Flight[] flights) {
        Flight minFlight = flights[0];

        for (Flight flight : flights) {
            if (flight.getFlightNumber().compareTo(minFlight.getFlightNumber()) < 0) {
                minFlight = flight;
            }
        }

        return minFlight;
    }

    private static Flight findMaxFlightNumber(Flight[] flights) {
        Flight maxFlight = flights[0];

        for (Flight flight : flights) {
            if (flight.getFlightNumber().compareTo(maxFlight.getFlightNumber()) > 0) {
                maxFlight = flight;
            }
        }

        return maxFlight;
    }
}
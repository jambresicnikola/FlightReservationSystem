package hr.java.flightreservationsystem.util;

import hr.java.flightreservationsystem.entity.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InputData {
    private InputData() {}

    public static User enterUser(Scanner sc) {
        Integer userRole;
        User user = null;
        Boolean inputValid = false;
        do {
            System.out.println("Choose 1 if you are a pilot or 2 if you are a passenger:");
            userRole =  sc.nextInt();

            if (userRole == 1) {
                sc.nextLine();
                user = InputData.enterPilotInfo(sc);
                inputValid = true;
                sc.nextLine();
            } else if (userRole == 2) {
                sc.nextLine();
                user = InputData.enterPassengerInfo(sc);
                inputValid = true;
                sc.nextLine();
            } else {
                System.out.println("Please choose a valid option.");
            }
        } while (Boolean.FALSE.equals(inputValid));

        return user;
    }

    private static Pilot enterPilotInfo(Scanner sc) {
        System.out.println("Enter Personal info:");
        System.out.println("First Name:");
        String  firstName = sc.nextLine();
        System.out.println("Last Name:");
        String lastName = sc.nextLine();
        System.out.println("Email:");
        String email = sc.nextLine();
        System.out.println("Hours flown:");
        Integer hoursFlown = sc.nextInt();

        return new Pilot(firstName, lastName, email, hoursFlown);
    }

    private static Passenger enterPassengerInfo(Scanner sc) {
        System.out.println("Enter Personal info:");
        System.out.println("First Name:");
        String  firstName = sc.nextLine();
        System.out.println("Last Name:");
        String lastName = sc.nextLine();
        System.out.println("Email:");
        String email = sc.nextLine();
        System.out.println("Age:");
        Integer age = sc.nextInt();

        return new Passenger(firstName, lastName, email, age);
    }

    public static Airline enterAirline(Scanner sc) {
        System.out.println("Enter Airline info:");
        System.out.println("Airline name:");
        String name = sc.nextLine();
        System.out.println("Airline code:");
        String code = sc.nextLine();

        return new Airline(name, code);
    }

    public static Flight enterFlight(Scanner sc, Airline[] airlines, Pilot[] pilots) {
        System.out.println("\nEnter Flight info:");
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
        LocalDate date = LocalDate.parse(flightDate, formatter);

        Boolean inputValid;
        Integer selectedIndex;
        Integer j;
        do {
            inputValid = true;

            System.out.println("Choose an airline:");

            j = 1;
            for (Airline airline : airlines) {
                System.out.println("\t" + j + ". " + airline.getName());
                j++;
            }

            selectedIndex = sc.nextInt();
            if (selectedIndex < 1 || selectedIndex > airlines.length) {
                System.out.println("Please enter a valid number.");
                inputValid = false;
            }
        } while (!inputValid);

        Airline airline = airlines[selectedIndex - 1];

        do {
            inputValid = true;

            System.out.println("Choose a pilot:");

            j = 0;
            for (Pilot pilot : pilots) {
                System.out.println("\t" + (j + 1) + ". " + pilot.getFirstName() + " " + pilot.getLastName());
                j++;
            }

            selectedIndex = sc.nextInt();
            if (selectedIndex < 1 || selectedIndex > pilots.length) {
                System.out.println("Please enter a valid number.");
                inputValid = false;
            }
        } while (!inputValid);
        sc.nextLine();

        Pilot pilot = pilots[selectedIndex - 1];

        Flight flight = new Flight
                .FlightBuilder(flightNumber, departureLocation, destination, airline, pilot)
                .build();

        flight.schedule(date);

        return flight;
    }

    public static Booking enterBooking(Scanner sc, Passenger[] passengers, Flight[] flights) {
        System.out.println("Enter booking info:");

        Integer selectedIndex;
        Boolean inputValid;
        do {
            inputValid = true;

            System.out.println("Choose a passenger:");

            Integer j = 0;
            for (Passenger passenger : passengers) {
                System.out.println("\t" + (j + 1) + ". " + passenger.getFirstName() + " " + passenger.getLastName());
                j++;
            }

            selectedIndex = sc.nextInt();
            if (selectedIndex < 1 || selectedIndex > passengers.length) {
                System.out.println("Please enter a valid number.");
                inputValid = false;
            }
        } while (!inputValid);

        Passenger passenger = passengers[selectedIndex - 1];

        do {
            inputValid = true;

            System.out.println("Choose a flight:");

            Integer j = 0;
            for (Flight flight : flights) {
                System.out.println("\t" + (j + 1) + ". " + flight.getFlightNumber());
                j++;
            }

            selectedIndex = sc.nextInt();
            if (selectedIndex < 1 || selectedIndex > flights.length) {
                System.out.println("Please enter a valid number.");
                inputValid = false;
            }
        } while (!inputValid);

        Flight flight = flights[selectedIndex - 1];

        Integer seatNumber, seatPriceInteger;
        do {
            System.out.println("Choose a seat number [1-25]:");
            seatNumber = sc.nextInt();
            if (seatNumber < 1 || seatNumber > 25) {
                System.out.println("Please enter a valid seat number.");
            }
        } while (seatNumber < 1 || seatNumber > 25);

        do {
            System.out.println("Enter seat price:");
            seatPriceInteger = sc.nextInt();
        } while (seatPriceInteger <= 0);

        SeatPrice seatPrice = new SeatPrice(seatPriceInteger);

        Booking booking = new Booking
                .BookingBuilder(passenger, flight, seatNumber, seatPrice).build();
        booking.reserve();

        return booking;
    }
}

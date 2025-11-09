package hr.java.flightreservationsystem.util;

import hr.java.flightreservationsystem.entity.*;
import hr.java.flightreservationsystem.exception.IllegalAirlineIndexSelectedException;
import hr.java.flightreservationsystem.exception.IllegalSeatNumberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputData {
    private static Logger logger = LoggerFactory.getLogger(InputData.class);

    private InputData() {}

    public static User enterUser(Scanner sc) {
        Integer userRole;
        User user = null;
        Boolean isValid;

        do {
            isValid = true;

            System.out.println("Choose 1 for pilot or 2 for passenger:");

            try {
                userRole = sc.nextInt();

                if (userRole == 1) {
                    sc.nextLine();
                    user = InputData.enterPilotInfo(sc);
                } else if (userRole == 2) {
                    sc.nextLine();
                    user = InputData.enterPassengerInfo(sc);
                } else {
                    System.out.println(Messages.PLEASE_CHOOSE_VALID_OPTION);
                    isValid = false;
                }
            } catch (InputMismatchException e) {
                logger.error(e.getMessage(), e);
                System.out.println(Messages.PLEASE_CHOOSE_VALID_OPTION);
                isValid = false;
                sc.nextLine();
            }
        } while (Boolean.FALSE.equals(isValid));

        sc.nextLine();

        return user;
    }

    private static Pilot enterPilotInfo(Scanner sc) {
        String firstName, lastName, email;
        Integer hoursFlown = 0;
        StringBuilder errorMessage;

        System.out.println("Enter Personal info:");
        do {
            errorMessage = new StringBuilder();

            System.out.println("First Name:");
            firstName = sc.nextLine();
            if (firstName.isEmpty()) {
                errorMessage.append("\n\t- First name cannot be empty.");
            }

            System.out.println("Last Name:");
            lastName = sc.nextLine();
            if (lastName.isEmpty()) {
                errorMessage.append("\n\t- Last name cannot be empty.");
            }

            System.out.println("Email:");
            email = sc.nextLine();
            if (email.isEmpty()) {
                errorMessage.append("\n\t- Email cannot be empty.");
            }

            try {
                System.out.println("Hours flown:");
                hoursFlown = sc.nextInt();

                if (hoursFlown < 0) {
                    errorMessage.append("\n\t- Hours flown cannot be a negative number.");
                }
            } catch (InputMismatchException e) {
                logger.error(e.getMessage(), e);
                errorMessage.append("\n\t- Hours flown must be a non-negative number.");
            }

            if (!errorMessage.isEmpty()) {
                sc.nextLine();
                System.out.println("You have following issues: " + errorMessage + "\nPlease try again.");
            }
        } while (!errorMessage.isEmpty());

        return new Pilot(firstName, lastName, email, hoursFlown);
    }

    private static Passenger enterPassengerInfo(Scanner sc) {
        String firstName, lastName, email;
        Integer age = 0;
        StringBuilder errorMessage;

        System.out.println("Enter Personal info:");
        do {
            errorMessage = new StringBuilder();

            System.out.println("First Name:");
            firstName = sc.nextLine();
            if (firstName.isEmpty()) {
                errorMessage.append("\n\t- First name cannot be empty.");
            }

            System.out.println("Last Name:");
            lastName = sc.nextLine();
            if (lastName.isEmpty()) {
                errorMessage.append("\n\t- Last name cannot be empty.");
            }

            System.out.println("Email:");
            email = sc.nextLine();
            if (email.isEmpty()) {
                errorMessage.append("\n\t- Email cannot be empty.");
            }

            try {
                System.out.println("Age:");
                age = sc.nextInt();

                if (age < 0) {
                    errorMessage.append("\n\t- Age cannot be a negative number.");
                }
            } catch (InputMismatchException e) {
                logger.error(e.getMessage(), e);
                errorMessage.append("\n\t- Age must be a non-negative number.");
            }

            if (!errorMessage.isEmpty()) {
                sc.nextLine();
                System.out.println("You have following issues: " + errorMessage + "\nPlease try again.");
            }
        } while (!errorMessage.isEmpty());

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
        Integer flightNumber;
        System.out.println("\nEnter Flight info:");
        do {
            try {
                System.out.println("Flight number:");
                flightNumber = sc.nextInt();

                if (flightNumber < 0) {
                    System.out.println("Flight number cannot be negative.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                logger.error(e.getMessage(), e);
                System.out.println("Please enter a valid flight number.");
                sc.nextLine();
            }
        } while (true);
        sc.nextLine();

        System.out.println("Departure location:");
        String departureLocation = sc.nextLine();
        System.out.println("Destination:");
        String destination = sc.nextLine();

        LocalDate date = null;
        do {
            try {
                System.out.println("Flight date [dd.MM.yyyy.]:");
                String flightDate = sc.nextLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
                date = LocalDate.parse(flightDate, formatter);

                break;
            } catch (DateTimeException e) {
                logger.error(e.getMessage(), e);
                System.out.println("Please enter a valid flight date.");
            }
        } while (true);

        Integer selectedIndex;
        do {
            try {
                System.out.println("Choose an airline:");

                Integer i = 1;
                for (Airline airline : airlines) {
                    System.out.println("\t" + i + ". " + airline.getName());
                    i++;
                }

                selectedIndex = chooseAirlineIndex(sc, airlines);

                break;
            } catch (IllegalAirlineIndexSelectedException | InputMismatchException e) {
                logger.error(e.getMessage(), e);
                System.out.println(Messages.PLEASE_CHOOSE_VALID_OPTION);

                if (e instanceof InputMismatchException) {
                    sc.nextLine();
                }
            }
        } while (true);
        Airline airline = airlines[selectedIndex - 1];

        do {
            try {
                System.out.println("Choose a pilot:");

                Integer i = 1;
                for (Pilot pilot : pilots) {
                    System.out.println("\t" + i + ". " + pilot.getFirstName() + " " + pilot.getLastName());
                    i++;
                }

                selectedIndex = sc.nextInt();

                if (selectedIndex < 1 || selectedIndex > pilots.length) {
                    System.out.println(Messages.PLEASE_CHOOSE_VALID_OPTION);
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                logger.error(e.getMessage(), e);
                System.out.println(Messages.PLEASE_CHOOSE_VALID_OPTION);
                sc.nextLine();
            }
        } while (true);
        sc.nextLine();
        Pilot pilot = pilots[selectedIndex - 1];

        Flight flight = new Flight
                .FlightBuilder(flightNumber, departureLocation, destination, airline, pilot)
                .build();

        flight.schedule(date);

        return flight;
    }

    private static Integer chooseAirlineIndex(Scanner sc, Airline[] airlines) throws IllegalAirlineIndexSelectedException {
        Integer selectedIndex = sc.nextInt();

        if (selectedIndex < 1 || selectedIndex > airlines.length) {
            throw new IllegalAirlineIndexSelectedException();
        }

        return selectedIndex;
    }

    public static Booking enterBooking(Scanner sc, Passenger[] passengers, Flight[] flights) {
        System.out.println("Enter booking info:");
        Integer selectedIndex;

        do {
            try {
                System.out.println("Choose a passenger:");

                Integer i = 1;
                for (Passenger passenger : passengers) {
                    System.out.println("\t" + i + ". " + passenger.getFirstName() + " " + passenger.getLastName());
                    i++;
                }

                selectedIndex = sc.nextInt();

                if (selectedIndex < 1 || selectedIndex > passengers.length) {
                    System.out.println(Messages.PLEASE_CHOOSE_VALID_OPTION);
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                logger.error(e.getMessage(), e);
                System.out.println(Messages.PLEASE_CHOOSE_VALID_OPTION);
                sc.nextLine();
            }
        } while (true);
        Passenger passenger = passengers[selectedIndex - 1];

        do {
            try {
                System.out.println("Choose a flight:");

                Integer i = 1;
                for (Flight flight : flights) {
                    System.out.println("\t" + i + ". " + flight.getFlightNumber());
                    i++;
                }

                selectedIndex = sc.nextInt();

                if (selectedIndex < 1 || selectedIndex > flights.length) {
                    System.out.println(Messages.PLEASE_CHOOSE_VALID_OPTION);
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                logger.error(e.getMessage(), e);
                System.out.println(Messages.PLEASE_CHOOSE_VALID_OPTION);
                sc.nextLine();
            }
        } while (true);
        Flight flight = flights[selectedIndex - 1];

        Integer seatNumber;
        while (true) {
            try {
                System.out.println("Choose a seat number [1-25]:");
                seatNumber = chooseSeatNumber(sc);

                break;
            } catch (IllegalSeatNumberException | InputMismatchException e) {
                logger.error(e.getMessage(), e);
                System.out.println("Please enter a valid seat number.");

                if (e instanceof InputMismatchException) {
                    sc.nextLine();
                }
            }
        }

        Integer seatPriceInteger;

        do {
            try {
                System.out.println("Enter seat price:");
                seatPriceInteger = sc.nextInt();

                if (seatPriceInteger < 1) {
                    System.out.println("Seat price must be a positive number.");
                    continue;
                }

                break;
            } catch (InputMismatchException e) {
                logger.error(e.getMessage(), e);
                System.out.println("Please enter a valid seat price.");
                sc.nextLine();
            }
        } while (true);
        sc.nextLine();
        SeatPrice seatPrice = new SeatPrice(seatPriceInteger);

        Booking booking = new Booking
                .BookingBuilder(passenger, flight, seatNumber, seatPrice).build();
        booking.reserve();

        return booking;
    }

    private static Integer chooseSeatNumber(Scanner sc) throws IllegalSeatNumberException {
        Integer seatNumber = sc.nextInt();

        if (seatNumber < 1 || seatNumber > 25) {
            throw new IllegalSeatNumberException();
        }

        return seatNumber;
    }
}

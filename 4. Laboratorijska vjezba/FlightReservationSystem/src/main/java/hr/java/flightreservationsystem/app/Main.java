package hr.java.flightreservationsystem.app;

import hr.java.flightreservationsystem.entity.*;
import hr.java.flightreservationsystem.util.InputData;
import hr.java.flightreservationsystem.util.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Integer NUMBER_OF_INPUTS = 3;

    public static void main(String[] args) {
        logger.info("Application has started.");

        System.out.println("Welcome to Flight Reservation System!");

        Scanner sc = new Scanner(System.in);

        Set<User> users = new HashSet<>();
        List<Airline> airlines = new ArrayList<>();
        List<Flight> flights = new ArrayList<>();
        List<Booking> bookings = new ArrayList<>();
        Integer inputIndex = 0;

        logger.info("User input has started...");
        System.out.println("\nWould you like to enter Users [yes/no]?");
        String userChoice;

        do {
            if (inputIndex == NUMBER_OF_INPUTS) {
                break;
            }

            userChoice = sc.nextLine();

            if (userChoice.equalsIgnoreCase("yes")) {
                users.add(InputData.enterUser(sc));

                inputIndex++;

                if (inputIndex < NUMBER_OF_INPUTS) {
                    System.out.println(Messages.WOULD_YOU_LIKE_TO_CONTINUE);
                }
            } else if (userChoice.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println(Messages.PLEASE_ENTER_YES_OR_NO);
            }
        } while (true);

        logger.info("Airline input has started...");
        inputIndex = 0;
        System.out.println("\nWould you like to enter Airlines [yes/no]?");

        do {
            if (inputIndex == NUMBER_OF_INPUTS) {
                break;
            }

            userChoice = sc.nextLine();

            if (userChoice.equalsIgnoreCase("yes")) {
                airlines.add(InputData.enterAirline(sc));

                inputIndex++;

                if (inputIndex < NUMBER_OF_INPUTS) {
                    System.out.println(Messages.WOULD_YOU_LIKE_TO_CONTINUE);
                }
            } else if (userChoice.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println(Messages.PLEASE_ENTER_YES_OR_NO);
            }
        } while(true);

        logger.info("Flight input has started...");
        inputIndex = 0;
        System.out.println("\nWould you like to enter Flights [yes/no]?");

        List<Pilot> pilots = users.stream()
                .filter(user -> user instanceof Pilot)
                .map(pilot -> (Pilot) pilot)
                .toList();

        do {
            if (inputIndex == NUMBER_OF_INPUTS) {
                break;
            }

            userChoice = sc.nextLine();

            if (userChoice.equalsIgnoreCase("yes")) {
                flights.add(InputData.enterFlight(sc, airlines, pilots));

                inputIndex++;

                if (inputIndex < NUMBER_OF_INPUTS) {
                    System.out.println(Messages.WOULD_YOU_LIKE_TO_CONTINUE);
                }
            } else if (userChoice.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println(Messages.PLEASE_ENTER_YES_OR_NO);
            }
        } while(true);

        logger.info("Booking input has started...");
        inputIndex = 0;
        System.out.println("\nWould you like to enter Bookings [yes/no]?");

        List<Passenger> passengers = users.stream()
                .filter(user -> user instanceof Passenger)
                .map(passenger -> (Passenger) passenger)
                .toList();

        do {
            if (inputIndex == NUMBER_OF_INPUTS) {
                break;
            }

            userChoice = sc.nextLine();

            if (userChoice.equalsIgnoreCase("yes")) {
                bookings.add(InputData.enterBooking(sc, passengers, flights));

                inputIndex++;

                if (inputIndex < NUMBER_OF_INPUTS) {
                    System.out.println(Messages.WOULD_YOU_LIKE_TO_CONTINUE);
                }
            } else if (userChoice.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println(Messages.PLEASE_ENTER_YES_OR_NO);
            }
        } while(true);

        logger.info("Finding min and max flight number.");
        System.out.println("\n-----------------------------------------------\n");
        System.out.println("Flight with minimal flight number: " + findMinFlightNumber(flights).getFlightNumber());
        System.out.println("Flight with maximal flight number: " + findMaxFlightNumber(flights).getFlightNumber());

        System.out.println("\nThank you for using our Flight Reservation System!");
    }

    private static Flight findMinFlightNumber(List<Flight> flights) {
        flights.sort(Comparator.comparingInt(Flight::getFlightNumber));
        return flights.getFirst();
    }

    private static Flight findMaxFlightNumber(List<Flight> flights) {
        flights.sort(Comparator.comparingInt(Flight::getFlightNumber));
        return flights.getLast();
    }
}
package hr.java.flightreservationsystem.app;

import hr.java.flightreservationsystem.entity.*;
import hr.java.flightreservationsystem.util.InputData;
import hr.java.flightreservationsystem.util.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Integer NUMBER_OF_INPUTS = 5;

    public static void main(String[] args) {
        logger.info("Application has started.");

        System.out.println("Welcome to Flight Reservation System!");

        Scanner sc = new Scanner(System.in);

        User[] users = new User[NUMBER_OF_INPUTS];
        Airline[] airlines = new Airline[NUMBER_OF_INPUTS];
        Flight[] flights = new Flight[NUMBER_OF_INPUTS];
        Booking[] bookings = new Booking[NUMBER_OF_INPUTS];
        Integer inputIndex = 0;

        logger.info("User input has started...");
        System.out.println("\nWould you like to enter Users [yes/no]?");
        String userChoice;
        Integer pilotCounter = 0, passengerCounter = 0;

        do {
            if (inputIndex == NUMBER_OF_INPUTS) {
                break;
            }

            userChoice = sc.nextLine();

            if (userChoice.equalsIgnoreCase("yes")) {
                users[inputIndex] = InputData.enterUser(sc);

                if (users[inputIndex] instanceof Pilot) {
                    pilotCounter++;
                } else {
                    passengerCounter++;
                }

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

        Pilot[] pilots = new Pilot[pilotCounter];
        Passenger[] passengers = new Passenger[passengerCounter];
        Integer i = 0, j = 0;
        for (User arrayUser : users) {
            if (arrayUser instanceof Pilot pilot) {
                pilots[i] = pilot;
                i++;
            }
            if (arrayUser instanceof Passenger passenger) {
                passengers[j] = passenger;
                j++;
            }
        }

        logger.info("Airline input has started...");
        inputIndex = 0;
        System.out.println("\nWould you like to enter Airlines [yes/no]?");

        do {
            if (inputIndex == NUMBER_OF_INPUTS) {
                break;
            }

            userChoice = sc.nextLine();

            if (userChoice.equalsIgnoreCase("yes")) {
                airlines[inputIndex] = InputData.enterAirline(sc);

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

        do {
            if (inputIndex == NUMBER_OF_INPUTS) {
                break;
            }

            userChoice = sc.nextLine();

            if (userChoice.equalsIgnoreCase("yes")) {
                flights[inputIndex] = InputData.enterFlight(sc, airlines, pilots);

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

        do {
            if (inputIndex == NUMBER_OF_INPUTS) {
                break;
            }

            userChoice = sc.nextLine();

            if (userChoice.equalsIgnoreCase("yes")) {
                bookings[inputIndex] = InputData.enterBooking(sc, passengers, flights);

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

        logger.info("\nFinding min and max flight number.");
        System.out.println("\n-----------------------------------------------\n");
        System.out.println("Flight with minimal flight number: " + findMinFlightNumber(flights).getFlightNumber());
        System.out.println("Flight with maximal flight number: " + findMaxFlightNumber(flights).getFlightNumber());

        System.out.println("\nThank you for using our Flight Reservation System!");
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
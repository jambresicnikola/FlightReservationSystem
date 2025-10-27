package hr.java.flightreservationsystem.app;

import hr.java.flightreservationsystem.entity.*;
import hr.java.flightreservationsystem.util.InputData;

import java.util.Scanner;

public class Main {
    private static final Integer NUMBER_OF_INPUTS = 5;

    public static void main(String[] args) {
        System.out.println("Welcome to Flight Reservation System!");

        Scanner sc = new Scanner(System.in);

        System.out.println("\nWould you like to enter Users [yes/no]?");
        String userChoice = sc.nextLine();

        User[] users = new User[NUMBER_OF_INPUTS];
        Airline[] airlines = new Airline[NUMBER_OF_INPUTS];
        Flight[] flights = new Flight[NUMBER_OF_INPUTS];
        Booking[] bookings = new Booking[NUMBER_OF_INPUTS];
        Integer i = 0;

        Integer pilotCounter = 0;
        Integer passengerCounter = 0;
        while ("yes".equalsIgnoreCase(userChoice)) {
            users[i] = InputData.enterUser(sc);

            if (users[i] instanceof Pilot) {
                pilotCounter++;
            } else {
                passengerCounter++;
            }

            if (i == (NUMBER_OF_INPUTS - 1)) {
                break;
            }

            System.out.println("Would you like to continue [yes/no]?");
            userChoice = sc.nextLine();

            i++;
        }

        Pilot[] pilots = new Pilot[pilotCounter];
        Passenger[] passengers = new Passenger[passengerCounter];
        Integer j = 0, k = 0;
        for (User arrayUser : users) {
            if (arrayUser instanceof Pilot pilot) {
                pilots[j] = pilot;
                j++;
            }
            if (arrayUser instanceof Passenger passenger) {
                passengers[k] = passenger;
                k++;
            }
        }

        i = 0;
        System.out.println("\nWould you like to enter Airlines [yes/no]?");
        userChoice = sc.nextLine();

        while ("yes".equalsIgnoreCase(userChoice)) {
            airlines[i]  = InputData.enterAirline(sc);

            if (i == (NUMBER_OF_INPUTS - 1)) {
                break;
            }

            System.out.println("Would you like to continue [yes/no]?");
            userChoice = sc.nextLine();

            i++;
        }

        i = 0;
        System.out.println("\nWould you like to enter Flights [yes/no]?");
        userChoice = sc.nextLine();

        while ("yes".equalsIgnoreCase(userChoice)) {
            flights[i] = InputData.enterFlight(sc, airlines, pilots);

            if (i == (NUMBER_OF_INPUTS - 1)) {
                break;
            }

            System.out.println("Would you like to continue [yes/no]?");
            userChoice = sc.nextLine();

            i++;
        }

        i = 0;
        System.out.println("\nWould you like to enter Bookings [yes/no]?");
        userChoice = sc.nextLine();

        while ("yes".equalsIgnoreCase(userChoice)) {
            bookings[i] = InputData.enterBooking(sc, passengers, flights);
            sc.nextLine();

            if (i == (NUMBER_OF_INPUTS - 1)) {
                break;
            }

            System.out.println("Would you like to continue [yes/no]?");
            userChoice = sc.nextLine();

            i++;
        }

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
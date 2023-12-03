package com.travel.agency;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        List<Trip> trips = TripDataGenerator.init();

        System.out.println( """ 
                             Available countries:
                             Poland
                             Ukraine
                             USA
                             Norway
                             Germany
                             """);
        System.out.print("Enter country to see available options: ");

        Scanner scanner = new Scanner(System.in);

        String country = scanner.next();

        trips = filterTripsByCountry(trips, country);

        trips.sort(Comparator.comparing(Trip::getStartDate).thenComparing(Trip::getDaysBetweenStartAndEndDate));

        verifyListOfTripsIsNotEmpty(trips, country);

        System.out.printf("Found %s trips to %s\n", trips.size(), country);

        System.out.println("""
                           If you want, you can specify minimum amount of days.
                           We will filter out trips that do not fit the criteria.
                           Print 'yes' to enter or 'no' to skip
                           """);

        int minimumDays = 0;

        while (true) {
            String option = scanner.next();

            if (option.equalsIgnoreCase("yes")) {
                System.out.print("Enter minimum amount of days: ");
                minimumDays = scanner.nextInt();
            } else if (option.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.print("Please print 'yes' to enter or 'no' to skip");
                continue;
            }

            break;
        }

        trips = filterTripsByPeriod(trips, minimumDays);

        verifyListOfTripsIsNotEmpty(trips, country);

        System.out.println();

        System.out.print("Please specify number of people: ");

        int numberOfPeople = scanner.nextInt();

        trips = filterTripsByAvailableTickets(trips, numberOfPeople);

        verifyListOfTripsIsNotEmpty(trips, country);

        System.out.printf("List of available trips to %s\n", country);

        for (Trip trip : trips) {
            System.out.println(trip);
        }

        System.out.print("Please select the more suitable trip for you and enter its number: ");

        Long tripId = scanner.nextLong();

        System.out.println();

        System.out.print("Please leave your phone or email to contact to provide checkout: ");

        String contactInfo = scanner.next();

        Trip chosenTrip = null;

        for (Trip trip : trips) {
            if (Objects.equals(trip.getTripId(), tripId)) {
                chosenTrip = trip;
                break;
            }
        }

        if (chosenTrip == null) {
            throw new IllegalArgumentException("You provided wrong trip number");
        }

        List<Ticket> allTickets = new ArrayList<>(chosenTrip.getTickets());

        List<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < numberOfPeople; i++) {
            Ticket ticket = allTickets.get(i);
            tickets.add(ticket);
            chosenTrip.getTickets().remove(ticket);
        }

        System.out.println();

        System.out.printf("""
                          Great! We are almost done!
                          Your ticket numbers:
                          %s
                          Our manager will contact you during the day via contact info you provided: %s
                          Have a nice day!
                          %n""", tickets, contactInfo);

    }

    private static List<Trip> filterTripsByAvailableTickets(List<Trip> trips, int numberOfPeople) {
        List<Trip> tripsWithEnoughTickets = new ArrayList<>();

        for (Trip trip : trips) {
            if (trip.getTickets().size() >= numberOfPeople) {
                tripsWithEnoughTickets.add(trip);
            }
        }
        return tripsWithEnoughTickets;
    }

    private static List<Trip> filterTripsByPeriod(List<Trip> trips, int minimumDays) {
        List<Trip> tripsFilteredByDays = new ArrayList<>();

        for (Trip trip : trips) {
            if (trip.getDaysBetweenStartAndEndDate() >= minimumDays) {
                tripsFilteredByDays.add(trip);
            }
        }
        return tripsFilteredByDays;
    }

    private static List<Trip> filterTripsByCountry(List<Trip> tripList, String country) {
        List<Trip> trips = new ArrayList<>();

        for (Trip trip : tripList) {

            if (trip.getCountry().name().equalsIgnoreCase(country)) {
                trips.add(trip);
            }
        }
        return trips;
    }

    private static void verifyListOfTripsIsNotEmpty(List<Trip> trips, String country) {
        if (trips.isEmpty()) {
            throw new RuntimeException("Sorry, there are no available trips to %s now.".formatted(country));
        }
    }




}

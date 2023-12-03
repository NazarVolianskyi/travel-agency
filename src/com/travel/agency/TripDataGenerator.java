package com.travel.agency;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class TripDataGenerator {

    private static long tripId = 10_000L;

    private static long ticketId = 100_000L;

    private static final Map<Country, Integer> ONE_DAY_PRICE_BY_COUNTRY = getOneDayPriceByCountry();

    private static final Map<Country, String> TRIP_DESCRIPTION_BY_COUNTRY = getTripDescriptionByCountry();


    public static List<Trip> init() {
        List<Trip> allTrips = new ArrayList<>();

        for (Country country : Country.values()) {

            List<Trip> trips = generateTripsFor(country);

            allTrips.addAll(trips);
        }

        return allTrips;
    }

    private static List<Trip> generateTripsFor(Country country) {

        int numberOfTrips = ThreadLocalRandom.current().nextInt(5, 10);

        List<Trip> trips = new ArrayList<>();

        for (int i = 1; i <= numberOfTrips; i++) {

            Trip trip = generateTrip(country);

            trips.add(trip);
        }

        return trips;
    }

    private static Trip generateTrip(Country country) {

        Integer oneDayPrice = ONE_DAY_PRICE_BY_COUNTRY.get(country);

        LocalDate startDate = LocalDate.now().plusDays(5);
        LocalDate endDate =
                startDate.plusDays(ThreadLocalRandom.current().nextInt(1, 10));

        Set<Ticket> tickets = generateTickets();



        String description = TRIP_DESCRIPTION_BY_COUNTRY.get(country);

        return new Trip(tripId++, description, country, oneDayPrice, startDate, endDate, tickets);
    }

    private static Set<Ticket> generateTickets() {

        int numberOfTickets = ThreadLocalRandom.current().nextInt(2, 20);

        Set<Ticket> tickets = new HashSet<>();

        for (int i = 1; i <= numberOfTickets; i++) {
            tickets.add(new Ticket(ticketId++));
        }

        return tickets;
    }

    private static Map<Country, Integer> getOneDayPriceByCountry() {

        Map<Country, Integer> countryPricePerDayMap = new HashMap<>();

        countryPricePerDayMap.put(Country.POLAND, 100);
        countryPricePerDayMap.put(Country.UKRAINE, 80);
        countryPricePerDayMap.put(Country.GERMANY, 130);
        countryPricePerDayMap.put(Country.NORWAY, 175);
        countryPricePerDayMap.put(Country.USA, 220);

        return countryPricePerDayMap;
    }

    private static Map<Country, String> getTripDescriptionByCountry() {
        Map<Country, String> tripDescriptionByCountry = new HashMap<>();

        tripDescriptionByCountry.put(Country.POLAND, "This trip includes visits to various major cities such as Warsaw, Krakow, Lodz, Rzeszów, Gdansk and Lublin");
        tripDescriptionByCountry.put(Country.UKRAINE, "This trip includes an interesting tour of the city of Lviv, visits to museums, bakeries and cultural monuments");
        tripDescriptionByCountry.put(Country.GERMANY, """
                                                      This is such an interesting trip, you will visit New York,
                                                      it includes a walking in Manhattan, Central Park, you will see various famous places and maybe meet celebrities");
                                                      """);
        tripDescriptionByCountry.put(Country.NORWAY, """
                                                    This trip includes a visiting Berlin, a capital of Germany,
                                                    you will Brandenburg Gate, Reichstag, memorial to the murdered Jews of Europe and you will taste national dishes of Germany");
                                                    """);
        tripDescriptionByCountry.put(Country.USA,
                                                    """
                                                      This trip includes a skiing holiday, you will learn how to snowboard
                                                     and ski, the name of the ski resort is Hemsdal Municipality)
                                                     """);

        return tripDescriptionByCountry;
    }
}

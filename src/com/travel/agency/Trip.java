package com.travel.agency;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Set;

public class Trip {
    private final Long tripId;
    private final String description;
    private final Country country;
    private final double oneDayPrice;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Set<Ticket> tickets;

    public Trip(Long tripId,
                String description,
                Country country,
                double oneDayPrice,
                LocalDate startDate,
                LocalDate endDate,
                Set<Ticket> tickets) {
        this.tripId = tripId;
        this.description = description;
        this.country = country;
        this.oneDayPrice = oneDayPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tickets = tickets;
    }

    public double getTotalPrice() {

        long daysBetween = getDaysBetweenStartAndEndDate();

        return oneDayPrice * daysBetween;
    }

    public long getDaysBetweenStartAndEndDate() {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    @Override
    public String toString() {
        return """
                Trip:
                     Trip number: #%s
                     Country: %s
                     Start date: %s
                     End date: %s
                     Price per person: %s $
                     Available tickets: %s
                     Description:
                         %s
                """
                .formatted(tripId, country.getName(), startDate, endDate, getTotalPrice(), tickets.size(), description);
    }

    public Long getTripId() {
        return tripId;
    }

    public String getDescription() {
        return description;
    }

    public Country getCountry() {
        return country;
    }

    public double getOneDayPrice() {
        return oneDayPrice;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Trip) obj;
        return Objects.equals(this.tripId, that.tripId) &&
                Objects.equals(this.description, that.description) &&
                Objects.equals(this.country, that.country) &&
                Double.doubleToLongBits(this.oneDayPrice) == Double.doubleToLongBits(that.oneDayPrice) &&
                Objects.equals(this.startDate, that.startDate) &&
                Objects.equals(this.endDate, that.endDate) &&
                Objects.equals(this.tickets, that.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId, description, country, oneDayPrice, startDate, endDate, tickets);
    }

}

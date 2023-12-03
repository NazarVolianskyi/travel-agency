package com.travel.agency;

import java.util.Objects;

public class Ticket {
    private final Long ticketId;

    public Ticket(Long ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String toString() {
        return "№" + ticketId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Ticket) obj;
        return Objects.equals(this.ticketId, that.ticketId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ticketId);
    }

}

package cleancode.studycafe.tobe.vo;

import cleancode.studycafe.tobe.model.LockerTicket;
import cleancode.studycafe.tobe.model.Ticket;

public class Result {

    private final Ticket ticket;
    private final LockerTicket lockerTicket;

    private Result(Ticket ticket, LockerTicket lockerTicket) {
        this.ticket = ticket;
        this.lockerTicket = lockerTicket;
    }

    public static Result of(Ticket ticket, LockerTicket lockerTicket) {
        return new Result(ticket, lockerTicket);
    }

    public static Result of(Ticket ticket) {
        return new Result(ticket, null);
    }

    public boolean hasLockerTicket() {
        return lockerTicket != null;
    }

    public Money calculateDiscountPrice() {
        return ticket.calculateDiscountPrice();
    }

    public Money calculateTotalPrice() {
        return ticket
            .getPrice()
            .subtract(calculateDiscountPrice())
            .add(lockerTicket != null ? lockerTicket.getPrice() : Money.ofZero());
    }

    public LockerTicket getLockerTicket() {
        return this.lockerTicket;
    }

    public Ticket getTicket() {
        return this.ticket;
    }
}

package cleancode.studycafe.tobe.model;

import java.util.ArrayList;
import java.util.List;

public class LockerTickets {

    public final List<LockerTicket> LOCKER_Tickets;

    private LockerTickets(List<LockerTicket> lockerTickets) {
        this.LOCKER_Tickets = lockerTickets;
    }

    public static LockerTickets of(List<LockerTicket> lockerTickets) {
        return new LockerTickets(lockerTickets);
    }

    public static LockerTickets of() {
        return new LockerTickets(new ArrayList<>());
    }

    public LockerTickets add(LockerTicket lockerTicket) {
        List<LockerTicket> list = new ArrayList<>(LOCKER_Tickets);
        list.add(lockerTicket);
        return of(list);
    }

    public LockerTickets filterByType(Type type) {
        List<LockerTicket> filteredResult = new ArrayList<>(LOCKER_Tickets).stream()
            .filter(ticket -> ticket.getType() == type)
            .toList();
        return of(filteredResult);
    }
    public LockerTickets filterByDuration(int duration) {

        List<LockerTicket> filteredResult = new ArrayList<>(LOCKER_Tickets).stream()
            .filter(ticket -> ticket.getDuration() == duration)
            .toList();
        return of(filteredResult);
    }

    public LockerTicket findFirstOrElseNull(){
        return LOCKER_Tickets.stream().findFirst().orElse(null);
    }
}

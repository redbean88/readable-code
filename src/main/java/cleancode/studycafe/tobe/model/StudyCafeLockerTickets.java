package cleancode.studycafe.tobe.model;

import java.util.ArrayList;
import java.util.List;

public class StudyCafeLockerTickets {

    public final List<StudyCafeLockerTicket> LOCKER_Tickets;

    private StudyCafeLockerTickets(List<StudyCafeLockerTicket> lockerTickets) {
        this.LOCKER_Tickets = lockerTickets;
    }

    public static StudyCafeLockerTickets of(List<StudyCafeLockerTicket> lockerTickets) {
        return new StudyCafeLockerTickets(lockerTickets);
    }

    public static StudyCafeLockerTickets of() {
        return new StudyCafeLockerTickets(new ArrayList<>());
    }

    public StudyCafeLockerTickets add(StudyCafeLockerTicket lockerPass) {
        List<StudyCafeLockerTicket> list = new ArrayList<>(LOCKER_Tickets);
        list.add(lockerPass);
        return of(list);
    }

    public StudyCafeLockerTickets filterByType(Type type) {
        List<StudyCafeLockerTicket> filteredResult = new ArrayList<>(LOCKER_Tickets).stream()
            .filter(ticket -> ticket.getType().equals(type))
            .toList();
        return of(filteredResult);
    }
    public StudyCafeLockerTickets filterByDuration(int duration) {

        List<StudyCafeLockerTicket> filteredResult = new ArrayList<>(LOCKER_Tickets).stream()
            .filter(ticket -> ticket.getDuration() == duration)
            .toList();
        return of(filteredResult);
    }

    public StudyCafeLockerTicket findFirstOrElseNull(){
        return LOCKER_Tickets.stream().findFirst().orElse(null);
    };
}

package cleancode.studycafe.tobe.model;

import java.util.ArrayList;
import java.util.List;

public class StudyCafeTickets {

    private final List<StudyCafeTicket> studyCafeTickets = new ArrayList<>();

    private StudyCafeTickets(List<StudyCafeTicket> studyCafeTickets) {
        this.studyCafeTickets.addAll(studyCafeTickets);
    }

    public static StudyCafeTickets of(List<StudyCafeTicket> studyCafeTickets) {
        return new StudyCafeTickets(studyCafeTickets);
    }
    public static StudyCafeTickets of() {
        return new StudyCafeTickets(new ArrayList<>());
    }


    public StudyCafeTickets add(StudyCafeTicket studyCafeTicket) {
        List<StudyCafeTicket> list = new ArrayList<>(studyCafeTickets);
        list.add(studyCafeTicket);
        return of(list);
    }

    public StudyCafeTickets filterByType(Type type) {
        List<StudyCafeTicket> filteredResult = new ArrayList<>(studyCafeTickets).stream()
            .filter(ticket -> ticket.getType() == type)
            .toList();
        return of(filteredResult);
    }

    public void display() {
        for (int index = 0; index < studyCafeTickets.size(); index++) {
            StudyCafeTicket pass = studyCafeTickets.get(index);
            System.out.println(String.format("%s. ", index + 1) + pass.display());
        }
    }

    public StudyCafeTicket get(int index) {
        return studyCafeTickets.get(index);
    }
}

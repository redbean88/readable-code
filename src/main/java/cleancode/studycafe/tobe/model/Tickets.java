package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.infra.exception.AppException;
import java.util.ArrayList;
import java.util.List;

public class Tickets {

    private final List<Ticket> tickets = new ArrayList<>();

    private Tickets(List<Ticket> tickets) {
        this.tickets.addAll(tickets);
    }

    public static Tickets of(List<Ticket> tickets) {
        return new Tickets(tickets);
    }

    public static Tickets of() {
        return new Tickets(new ArrayList<>());
    }

    public Tickets add(Ticket ticket) {
        List<Ticket> list = new ArrayList<>(tickets);
        list.add(ticket);
        return of(list);
    }

    public Tickets filterByType(Type type) {
        List<Ticket> filteredResult = new ArrayList<>(tickets).stream()
            .filter(ticket -> ticket.getType() == type)
            .toList();
        return of(filteredResult);
    }

    public void display() {
        tickets.stream().map(Ticket::display).forEach(System.out::println);
        for (int index = 0; index < tickets.size(); index++) {
            Ticket ticket = tickets.get(index);
            System.out.println(String.format("%s. ", index + 1) + ticket.display());
        }
    }

    public Ticket get(int index) {
        if (index > tickets.size() - 1) {
            throw new AppException("잘못된 입력입니다.");
        }
        return tickets.get(index);
    }
}

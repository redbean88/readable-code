package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.infra.exception.AppException;
import java.util.ArrayList;
import java.util.List;

public class Tickets {

    private final List<Ticket> list = new ArrayList<>();

    private Tickets(List<Ticket> list) {
        this.list.addAll(list);
    }

    public static Tickets of(List<Ticket> tickets) {
        return new Tickets(tickets);
    }

    public static Tickets of() {
        return new Tickets(new ArrayList<>());
    }

    public Tickets add(Ticket ticket) {
        List<Ticket> list = new ArrayList<>(this.list);
        list.add(ticket);
        return of(list);
    }

    public Tickets filterByType(Type type) {
        List<Ticket> filteredResult = new ArrayList<>(list).stream()
            .filter(ticket -> ticket.getType() == type)
            .toList();
        return of(filteredResult);
    }

    public Ticket get(int index) {
        if (index > list.size() - 1) {
            throw new AppException("잘못된 입력입니다.");
        }
        return list.get(index);
    }

    public List<Ticket> getList() {
        return this.list;
    }
}

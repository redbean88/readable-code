package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.LockerTicket;
import cleancode.studycafe.tobe.model.Ticket;
import cleancode.studycafe.tobe.model.LockerTickets;

public interface LockerRepository {

    LockerTickets findAll();
    LockerTicket findByStudyCafeTicket(Ticket selectedTicket);
}

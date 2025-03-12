package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.StudyCafeLockerTicket;
import cleancode.studycafe.tobe.model.StudyCafeTicket;
import cleancode.studycafe.tobe.model.StudyCafeLockerTickets;

public interface LockerRepository {

    StudyCafeLockerTickets findAll();
    StudyCafeLockerTicket findByStateAndDuration(StudyCafeTicket selectedStudyCafeTicket);
}

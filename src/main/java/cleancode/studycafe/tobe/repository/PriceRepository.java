package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.Ticket;
import java.util.List;

public interface PriceRepository {

    List<StudyCafePass> findAll();
    List<StudyCafePass> findByTicket(Ticket ticket);
}

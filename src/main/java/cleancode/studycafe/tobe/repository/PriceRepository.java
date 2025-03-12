package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.StudyCafeTicket;
import cleancode.studycafe.tobe.model.Type;
import java.util.List;

public interface PriceRepository {

    List<StudyCafeTicket> findAll();
    List<StudyCafeTicket> findByTickets(Type type);
}

package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.Type;
import cleancode.studycafe.tobe.model.StudyCafeTickets;

public interface PriceRepository {

    StudyCafeTickets findAll();
    StudyCafeTickets findByTickets(Type type);
}

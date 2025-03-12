package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.Type;
import cleancode.studycafe.tobe.model.Tickets;

public interface PriceRepository {

    Tickets findAll();
    Tickets findByType(Type type);
}

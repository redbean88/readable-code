package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.Ticket;
import java.util.List;

public interface LockerRepository {

    List<StudyCafeLockerPass> findAll();
    StudyCafeLockerPass findByStateAndDuration(Ticket ticket, int duration);
}

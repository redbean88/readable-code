package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.Type;
import java.util.List;

public interface LockerRepository {

    List<StudyCafeLockerPass> findAll();
    StudyCafeLockerPass findByStateAndDuration(Type type, int duration);
}

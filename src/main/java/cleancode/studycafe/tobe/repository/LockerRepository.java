package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import java.util.List;

public interface LockerRepository {

    List<StudyCafeLockerPass> findAll();
}

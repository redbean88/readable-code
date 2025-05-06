package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.exception.FileReadException;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import java.util.List;

public interface StudyCafeRepository {

    List<StudyCafePass> findAllStudyCafePasses() throws FileReadException;
    List<StudyCafeLockerPass> findAllLockerPasses() throws FileReadException;
}

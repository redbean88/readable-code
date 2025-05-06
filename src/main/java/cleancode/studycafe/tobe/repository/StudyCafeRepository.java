package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.exception.FileReadException;
import cleancode.studycafe.tobe.model.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.model.StudyCafePasses;

public interface StudyCafeRepository {

    StudyCafePasses findAllStudyCafePasses() throws FileReadException;
    StudyCafeLockerPasses findAllLockerPasses() throws FileReadException;
}

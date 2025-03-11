package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.StudyCafePass;
import java.util.List;

public interface PriceRepository {

    List<StudyCafePass> findAll();
}

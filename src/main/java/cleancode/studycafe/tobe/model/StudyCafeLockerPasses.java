package cleancode.studycafe.tobe.model;

import java.util.List;
import java.util.Optional;

public class StudyCafeLockerPasses {

    private final List<StudyCafeLockerPass> studyCafeLockerPassList;

    public StudyCafeLockerPasses(List<StudyCafeLockerPass> studyCafeLockerPassList) {
        this.studyCafeLockerPassList = studyCafeLockerPassList;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> studyCafeLockerPassList) {
        return new StudyCafeLockerPasses(studyCafeLockerPassList);
    }

    public List<StudyCafeLockerPass> getStudyCafeLockerPassList() {
        return studyCafeLockerPassList;
    }

    public Optional<StudyCafeLockerPass> isLockerUseAvailable(StudyCafePass selectedPass) {
        return studyCafeLockerPassList.stream()
            .filter(selectedPass::isEqualsPassTypeBy)
            .filter(selectedPass::isEqualDuration)
            .findFirst();
    }
}

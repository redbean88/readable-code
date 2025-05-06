package cleancode.studycafe.tobe.model;

import java.util.List;

public class StudyCafePasses {

    private final List<StudyCafePass> studyCafePassList;

    public StudyCafePasses(List<StudyCafePass> studyCafePassList) {
        this.studyCafePassList = studyCafePassList;
    }

    public static StudyCafePasses of(List<StudyCafePass> studyCafePassList) {
        return new StudyCafePasses(studyCafePassList);
    }

    public List<StudyCafePass> getStudyCafePassList() {
        return studyCafePassList;
    }

    public List<StudyCafePass> isEqualsPassTypeBy(StudyCafePassType studyCafePassType) {
        return studyCafePassList.stream()
            .filter(studyCafePassType::isEqualsPassTypeBy)
            .toList();
    }
}

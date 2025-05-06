package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.controller.Controller;
import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.exception.FileReadException;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.model.StudyCafePasses;
import cleancode.studycafe.tobe.repository.StudyCafeRepository;
import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {


    private final Controller controller;
    private final StudyCafeRepository studyCafeRepository;

    public StudyCafePassMachine(StudyCafeRepository studyCafeRepository, Controller controller) {
        this.studyCafeRepository = studyCafeRepository;
        this.controller = controller;
    }

    public void run() {
        try {
            controller.showWelcomeMessage();
            controller.showAnnouncement();

            StudyCafePass selectedPass = selectedStudyCafePassByUser();
            Optional<StudyCafeLockerPass> optionalLockerPass = selectedStudyCafeLockerPassByUser(selectedPass);

            optionalLockerPass.ifPresentOrElse(
                lockerPass -> controller.showPassOrderSummary(selectedPass, lockerPass),
                () -> controller.showPassOrderSummary(selectedPass)
            );

        } catch (AppException e) {
            controller.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            controller.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private Optional<StudyCafeLockerPass> selectedStudyCafeLockerPassByUser(
        StudyCafePass selectedPass)
        throws FileReadException {
        StudyCafeLockerPasses lockerPasses = studyCafeRepository.findAllLockerPasses();
        Optional<StudyCafeLockerPass> lockerPass = lockerPasses.isLockerUseAvailable(selectedPass);

        if (lockerPass.isPresent()) {
            controller.askLockerPass(lockerPass.get());
            boolean isLockerSelected = controller.isLockerSelected();
            if (isLockerSelected) {
                return lockerPass;
            }
        }
        return Optional.empty();
    }

    private StudyCafePass selectedStudyCafePassByUser() throws FileReadException {
        controller.askPassTypeSelection();
        StudyCafePassType studyCafePassType = controller.getPassTypeBy();
        StudyCafePasses studyCafePasses = studyCafeRepository.findAllStudyCafePasses();

        List<StudyCafePass> passes = studyCafePasses.isEqualsPassTypeBy(studyCafePassType);

        controller.showPassListForSelection(passes);
        return controller.getSelectPass(passes);
    }
}

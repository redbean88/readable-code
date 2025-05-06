package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.model.StudyCafePasses;
import cleancode.studycafe.tobe.repository.StudyCafeRepository;
import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();

    private final OutputHandler outputHandler = new OutputHandler();

    private final StudyCafeRepository studyCafeRepository;

    public StudyCafePassMachine(StudyCafeRepository studyCafeRepository) {
        this.studyCafeRepository = studyCafeRepository;
    }

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();

            StudyCafePassType studyCafePassType = inputHandler.getPassTypeBy();
            StudyCafePasses studyCafePasses = studyCafeRepository.findAllStudyCafePasses();

            List<StudyCafePass> hourlyPasses = studyCafePasses.isEqualsPassTypeBy(studyCafePassType);

            outputHandler.showPassListForSelection(hourlyPasses);
            StudyCafePass selectedPass = inputHandler.getSelectPass(hourlyPasses);

            StudyCafeLockerPasses lockerPasses = studyCafeRepository.findAllLockerPasses();
            Optional<StudyCafeLockerPass> lockerPass = lockerPasses.isLockerUseAvailable(selectedPass);

            if (lockerPass.isPresent()) {
                outputHandler.askLockerPass(lockerPass.get());
                boolean lockerSelection = inputHandler.getLockerSelection();

                if (lockerSelection) {
                    outputHandler.showPassOrderSummary(selectedPass, lockerPass.get());
                } else {
                    outputHandler.showPassOrderSummary(selectedPass);
                }
            } else {
                outputHandler.showPassOrderSummary(selectedPass);
            }

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }
}

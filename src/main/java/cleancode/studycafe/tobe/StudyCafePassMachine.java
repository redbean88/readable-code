package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.repository.StudyCafeRepository;
import java.util.List;
import java.util.Scanner;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();

    private static final Scanner SCANNER = new Scanner(System.in);
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
            String userInput = SCANNER.nextLine();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeBy(userInput);


            if (studyCafePassType == StudyCafePassType.HOURLY) {
                List<StudyCafePass> studyCafePasses = studyCafeRepository.findAllStudyCafePasses();
                List<StudyCafePass> hourlyPasses = studyCafePasses.stream()
                    .filter(
                        studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.HOURLY)
                    .toList();
                outputHandler.showPassListForSelection(hourlyPasses);
                userInput = SCANNER.nextLine();
                StudyCafePass selectedPass = inputHandler.getSelectPass(hourlyPasses, userInput);
                outputHandler.showPassOrderSummary(selectedPass, null);
            } else if (studyCafePassType == StudyCafePassType.WEEKLY) {
                List<StudyCafePass> studyCafePasses = studyCafeRepository.findAllStudyCafePasses();
                List<StudyCafePass> weeklyPasses = studyCafePasses.stream()
                    .filter(
                        studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.WEEKLY)
                    .toList();
                outputHandler.showPassListForSelection(weeklyPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(weeklyPasses, userInput);
                outputHandler.showPassOrderSummary(selectedPass, null);
            } else if (studyCafePassType == StudyCafePassType.FIXED) {
                List<StudyCafePass> studyCafePasses = studyCafeRepository.findAllStudyCafePasses();
                List<StudyCafePass> fixedPasses = studyCafePasses.stream()
                    .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.FIXED)
                    .toList();
                outputHandler.showPassListForSelection(fixedPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(fixedPasses, userInput);

                List<StudyCafeLockerPass> lockerPasses = studyCafeRepository.findAllLockerPasses();
                StudyCafeLockerPass lockerPass = lockerPasses.stream()
                    .filter(option ->
                        option.getPassType() == selectedPass.getPassType()
                            && option.getDuration() == selectedPass.getDuration()
                    )
                    .findFirst()
                    .orElse(null);

                boolean lockerSelection = false;
                if (lockerPass != null) {
                    outputHandler.askLockerPass(lockerPass);
                    lockerSelection = inputHandler.getLockerSelection();
                }

                if (lockerSelection) {
                    outputHandler.showPassOrderSummary(selectedPass, lockerPass);
                } else {
                    outputHandler.showPassOrderSummary(selectedPass, null);
                }
            }
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

}

package cleancode.studycafe.tobe.controller;

import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.model.Order;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import java.util.List;

public class Controller {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public Controller(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public void showWelcomeMessage() {
        outputHandler.showWelcomeMessage();
    }

    public void showAnnouncement() {
        outputHandler.showAnnouncement();
    }

    public void askPassTypeSelection() {
        outputHandler.askPassTypeSelection();
    }

    public StudyCafePassType getPassTypeBy() {
        return inputHandler.getPassTypeBy();
    }

    public void showPassListForSelection(List<StudyCafePass> passes) {
        outputHandler.showPassListForSelection(passes);
    }

    public StudyCafePass getSelectPass(List<StudyCafePass> passes) {
        return inputHandler.getSelectPass(passes);
    }

    public void askLockerPass(StudyCafeLockerPass studyCafeLockerPass) {
        outputHandler.askLockerPass(studyCafeLockerPass);
    }

    public boolean isLockerSelected() {
        return inputHandler.isLockerSelected();
    }

    public void showPassOrderSummary(Order selectedPass) {
        outputHandler.showPassOrderSummary(selectedPass);
    }

    public void showSimpleMessage(String message) {
        outputHandler.showSimpleMessage(message);
    }

}

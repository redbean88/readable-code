package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.Ticket;

import cleancode.studycafe.tobe.repository.LockerRepository;
import cleancode.studycafe.tobe.repository.LockerRepositoryImp;
import cleancode.studycafe.tobe.repository.PriceRepository;
import cleancode.studycafe.tobe.repository.PriceRepositoryImp;
import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final PriceRepository priceRepository;
    private final LockerRepository lockerRepository;

    public StudyCafePassMachine() {
        this.priceRepository = new PriceRepositoryImp();
        this.lockerRepository = new LockerRepositoryImp();
    }

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            Ticket ticket = inputHandler.getPassTypeSelectingUserAction();

            if (ticket == Ticket.시간단위이용권) {
                List<StudyCafePass> studyCafePasses = priceRepository.findAll();
                List<StudyCafePass> hourlyPasses = studyCafePasses.stream()
                    .filter(studyCafePass -> studyCafePass.getPassType() == Ticket.시간단위이용권)
                    .toList();
                outputHandler.showPassListForSelection(hourlyPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(hourlyPasses);
                outputHandler.showPassOrderSummary(selectedPass, null);
            } else if (ticket == Ticket.주단위이용권) {
                List<StudyCafePass> studyCafePasses = priceRepository.findAll();
                List<StudyCafePass> weeklyPasses = studyCafePasses.stream()
                    .filter(studyCafePass -> studyCafePass.getPassType() == Ticket.주단위이용권)
                    .toList();
                outputHandler.showPassListForSelection(weeklyPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(weeklyPasses);
                outputHandler.showPassOrderSummary(selectedPass, null);
            } else if (ticket == Ticket.일인고정석) {
                List<StudyCafePass> studyCafePasses = priceRepository.findAll();
                List<StudyCafePass> fixedPasses = studyCafePasses.stream()
                    .filter(studyCafePass -> studyCafePass.getPassType() == Ticket.일인고정석)
                    .toList();
                outputHandler.showPassListForSelection(fixedPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(fixedPasses);

                List<StudyCafeLockerPass> lockerPasses = lockerRepository.findAll();
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

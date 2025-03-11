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

            List<StudyCafePass> hourlyPasses = priceRepository.findByTicket(ticket);
            outputHandler.showPassListForSelection(hourlyPasses);
            StudyCafePass selectedPass = inputHandler.getSelectPass(hourlyPasses);

            switch (ticket) {
                case HOURLY:
                case WEEKLY:
                    outputHandler.showPassOrderSummary(selectedPass, null);
                    break;
                case FIXED:
                    StudyCafeLockerPass lockerPass = lockerRepository.findByStateAndDuration(selectedPass.getPassType(), selectedPass.getDuration());
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
                    break;
                default:
                    outputHandler.showSimpleMessage("선택 할 수 없는 구분입니다.");
            }
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

}

package cleancode.studycafe.tobe.service;

import cleancode.studycafe.tobe.infra.exception.AppException;
import cleancode.studycafe.tobe.infra.Kiosk;
import cleancode.studycafe.tobe.infra.io.InputHandler;
import cleancode.studycafe.tobe.infra.io.OutputHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafeTicket;
import cleancode.studycafe.tobe.model.Type;
import cleancode.studycafe.tobe.repository.LockerRepository;
import cleancode.studycafe.tobe.repository.PriceRepository;
import cleancode.studycafe.tobe.vo.Result;
import java.util.List;

public class TicketKiosk implements Kiosk {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final PriceRepository priceRepository;
    private final LockerRepository lockerRepository;

    public TicketKiosk(InputHandler inputHandler, OutputHandler outputHandler,
        PriceRepository priceRepository, LockerRepository lockerRepository) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.priceRepository = priceRepository;
        this.lockerRepository = lockerRepository;
    }

    @Override
    public void run() {
        try {
            notice();
            Result result = chooseTicketByUser();
            showResult(result);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void notice() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
        outputHandler.askPassTypeSelection();
    }

    private void showResult(Result result) {
        if(result == null) {
            outputHandler.showSimpleMessage("선택 할 수 없는 구분입니다.");
        }else {
            outputHandler.showPassOrderSummary(result);
        }
    }

    private Result chooseTicketByUser() {
        Type type = inputHandler.getTicketSelectedByUser();

        List<StudyCafeTicket> tickets = priceRepository.findByTickets(type);
        outputHandler.showPassListForSelection(tickets);
        StudyCafeTicket selectedPass = inputHandler.getSelectPass(tickets);

        switch (type) {
            case HOURLY:
            case WEEKLY:
                return Result.of(selectedPass);
            case FIXED:
                StudyCafeLockerPass lockerPass = lockerRepository.findByStateAndDuration(
                    selectedPass.getTicket(), selectedPass.getDuration());
                boolean lockerSelection = false;
                if (lockerPass != null) {
                    outputHandler.askLockerPass(lockerPass);
                    lockerSelection = inputHandler.getLockerSelection();
                }

                if (lockerSelection) {
                    return Result.of(selectedPass, lockerPass);
                } else {
                    return Result.of(selectedPass);
                }
            default:
                return null;
        }
    }

}

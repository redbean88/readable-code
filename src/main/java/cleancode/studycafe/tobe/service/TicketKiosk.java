package cleancode.studycafe.tobe.service;

import cleancode.studycafe.tobe.infra.exception.AppException;
import cleancode.studycafe.tobe.infra.Kiosk;
import cleancode.studycafe.tobe.infra.io.InputHandler;
import cleancode.studycafe.tobe.infra.io.OutputHandler;
import cleancode.studycafe.tobe.model.LockerTicket;
import cleancode.studycafe.tobe.model.Ticket;
import cleancode.studycafe.tobe.model.Type;
import cleancode.studycafe.tobe.repository.LockerRepository;
import cleancode.studycafe.tobe.repository.PriceRepository;
import cleancode.studycafe.tobe.vo.Result;
import cleancode.studycafe.tobe.model.Tickets;

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
        outputHandler.askTypeSelection();
    }

    private void showResult(Result result) {
        if(result == null) {
            outputHandler.showSimpleMessage("선택 할 수 없는 구분입니다.");
        }else {
            outputHandler.showTicketOrderSummary(result);
        }
    }

    private Result chooseTicketByUser() {
        Type type = inputHandler.getTicketSelectedByUser();

        Tickets tickets = priceRepository.findByType(type);
        outputHandler.showTicketsForSelection(tickets);
        Ticket selectedTicket = inputHandler.getSelectTicket(tickets);

        switch (type) {
            case HOURLY, WEEKLY:
                return Result.of(selectedTicket);
            case FIXED:
                LockerTicket lockerTicket = lockerRepository.findByStudyCafeTicket(selectedTicket);
                boolean lockerSelection = isLockerSelectionByUser(lockerTicket);
                if (lockerSelection) {
                    return Result.of(selectedTicket, lockerTicket);
                } else {
                    return Result.of(selectedTicket);
                }
            default:
                return null;
        }
    }

    private boolean isLockerSelectionByUser(LockerTicket lockerTicket) {
        boolean lockerSelection = false;
        if (lockerTicket != null) {
            outputHandler.askLockerTicket(lockerTicket);
            lockerSelection = inputHandler.getLockerSelection();
        }
        return lockerSelection;
    }

}

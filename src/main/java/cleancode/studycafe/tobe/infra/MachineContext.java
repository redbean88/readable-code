package cleancode.studycafe.tobe.infra;

import cleancode.studycafe.tobe.service.TicketKiosk;
import cleancode.studycafe.tobe.infra.io.InputHandler;
import cleancode.studycafe.tobe.infra.io.OutputHandler;
import cleancode.studycafe.tobe.infra.io.LockerRepositoryImp;
import cleancode.studycafe.tobe.infra.io.PriceRepositoryImp;

public class MachineContext {

    private MachineContext() {}

    private static class Holder {

        private static final TicketKiosk STUDY_CAFE_TICKET_MACHINE = new TicketKiosk(
            new InputHandler(),
            new OutputHandler(),
            new PriceRepositoryImp(),
            new LockerRepositoryImp()
        );
    }

    public static TicketKiosk getInstance() {
        return Holder.STUDY_CAFE_TICKET_MACHINE;
    }

}

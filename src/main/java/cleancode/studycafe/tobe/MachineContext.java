package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.repository.LockerRepositoryImp;
import cleancode.studycafe.tobe.repository.PriceRepositoryImp;

public class MachineContext {

    private MachineContext() {}

    private static class Holder{
        private static final TicketKiosk STUDY_CAFE_PASS_MACHINE = new TicketKiosk( new InputHandler(), new OutputHandler(),  new PriceRepositoryImp(),  new LockerRepositoryImp());
    }

    public static TicketKiosk getInstance() {
        return Holder.STUDY_CAFE_PASS_MACHINE;
    }

}

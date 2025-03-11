package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.repository.LockerRepositoryImp;
import cleancode.studycafe.tobe.repository.PriceRepositoryImp;

public class StudyCafePassMachineContainer {

    private StudyCafePassMachineContainer() {}

    private static class Holder{
        private static final StudyCafePassMachine STUDY_CAFE_PASS_MACHINE = new StudyCafePassMachine( new InputHandler(), new OutputHandler(),  new PriceRepositoryImp(),  new LockerRepositoryImp());
    }

    public static StudyCafePassMachine getInstance() {
        return Holder.STUDY_CAFE_PASS_MACHINE;
    }

}

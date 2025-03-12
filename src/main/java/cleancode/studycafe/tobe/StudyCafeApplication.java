package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.infra.Kiosk;
import cleancode.studycafe.tobe.infra.MachineContext;

public class StudyCafeApplication {

    public static void main(String[] args) {
        Kiosk kiosk = MachineContext.getInstance();
        kiosk.run();
    }

}

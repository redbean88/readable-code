package cleancode.studycafe.tobe;

public class StudyCafeApplication {

    public static void main(String[] args) {
        Kiosk kiosk = MachineContext.getInstance();
        kiosk.run();
    }

}

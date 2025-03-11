package cleancode.studycafe.tobe;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafePassMachine studyCafePassMachine = StudyCafePassMachineContainer.getInstance();
        studyCafePassMachine.run();
    }

}

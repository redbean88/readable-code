package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.controller.Controller;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeRepositoryImp;
import cleancode.studycafe.tobe.repository.StudyCafeRepository;

public class StudyCafeApplication {

    public static void main(String[] args) {

        String lockerCsv = "src/main/resources/cleancode/studycafe/locker.csv";
        String passListCsv = "src/main/resources/cleancode/studycafe/pass-list.csv";
        StudyCafeRepository studyCafeRepository = new StudyCafeRepositoryImp(passListCsv, lockerCsv);
        Controller controller = new Controller(new InputHandler(), new OutputHandler());

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(studyCafeRepository, controller);
        studyCafePassMachine.run();
    }

}

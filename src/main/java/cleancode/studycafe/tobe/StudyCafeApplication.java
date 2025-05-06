package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.StudyCafeRepositoryImp;
import cleancode.studycafe.tobe.repository.StudyCafeRepository;

public class StudyCafeApplication {

    public static void main(String[] args) {

        String lockerCsv = "src/main/resources/cleancode/studycafe/locker.csv";
        String passListCsv = "src/main/resources/cleancode/studycafe/pass-list.csv";

        StudyCafeRepository studyCafeRepository = new StudyCafeRepositoryImp(passListCsv, lockerCsv);
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(studyCafeRepository);
        studyCafePassMachine.run();
    }

}

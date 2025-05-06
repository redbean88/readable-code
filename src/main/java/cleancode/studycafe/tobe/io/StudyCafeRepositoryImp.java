package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.exception.FileReadException;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.model.StudyCafePasses;
import cleancode.studycafe.tobe.repository.StudyCafeRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafeRepositoryImp implements StudyCafeRepository {

    private final String passListCsv;
    private final String lockerCsv;

    public StudyCafeRepositoryImp(String passListCsv, String lockerCsv) {
        this.passListCsv = passListCsv;
        this.lockerCsv = lockerCsv;
    }

    public StudyCafePasses findAllStudyCafePasses() throws FileReadException {
        try {
            List<String> lines = Files.readAllLines(Paths.get(passListCsv));
            List<StudyCafePass> studyCafePasses = lines.stream().map(line -> {
                String[] values = line.split(",");
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);
                double discountRate = Double.parseDouble(values[3]);

                return StudyCafePass.of(studyCafePassType, duration, price, discountRate);
            }).toList();

            return StudyCafePasses.of(studyCafePasses);
        } catch (IOException e) {
            throw new FileReadException("파일을 읽는데 실패했습니다.");
        }
    }

    public StudyCafeLockerPasses findAllLockerPasses() throws FileReadException {
        try {
            List<String> lines = Files.readAllLines(Paths.get(lockerCsv));
            List<StudyCafeLockerPass> lockerPasses = lines.stream().map(line -> {
                String[] values = line.split(",");
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);

                return StudyCafeLockerPass.of(studyCafePassType, duration, price);
            }).toList();

            return StudyCafeLockerPasses.of(lockerPasses);
        } catch (IOException e) {
            throw new FileReadException("파일을 읽는데 실패했습니다.");
        }
    }

}

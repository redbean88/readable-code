package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.StudyCafeLockerTicket;
import cleancode.studycafe.tobe.model.StudyCafeTicket;
import cleancode.studycafe.tobe.model.Type;
import cleancode.studycafe.tobe.model.StudyCafeLockerTickets;
import cleancode.studycafe.tobe.vo.Money;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LockerRepositoryImp implements LockerRepository {

    public StudyCafeLockerTickets studyCafeLockerTickets = StudyCafeLockerTickets.of();

    public LockerRepositoryImp() {
        try {
            List<String> lines = Files.readAllLines(
                Paths.get("src/main/resources/cleancode/studycafe/locker.csv"));
            for (String line : lines) {
                String[] values = line.split(",");

                StudyCafeLockerTicket lockerPass = StudyCafeLockerTicket.of(
                    Type.valueOf(values[0]),
                    Integer.parseInt(values[1]),
                    Money.of(Integer.parseInt(values[2]))
                );

                this.studyCafeLockerTickets = studyCafeLockerTickets.add(lockerPass);
            }
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }

    }

    @Override
    public StudyCafeLockerTickets findAll() {
        return studyCafeLockerTickets;
    }

    @Override
    public StudyCafeLockerTicket findByStateAndDuration(StudyCafeTicket selectedStudyCafeTicket) {
        return findAll()
                .filterByType(selectedStudyCafeTicket.getType())
                .filterByDuration(selectedStudyCafeTicket.getDuration())
                .findFirstOrElseNull();
    }
}

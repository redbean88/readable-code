package cleancode.studycafe.tobe.infra.io;

import cleancode.studycafe.tobe.model.LockerTicket;
import cleancode.studycafe.tobe.model.Ticket;
import cleancode.studycafe.tobe.model.Type;
import cleancode.studycafe.tobe.model.LockerTickets;
import cleancode.studycafe.tobe.repository.LockerRepository;
import cleancode.studycafe.tobe.vo.Money;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LockerRepositoryImp implements LockerRepository {

    private LockerTickets lockerTickets = LockerTickets.of();

    public LockerRepositoryImp() {
        try {
            List<String> lines = Files.readAllLines(
                Paths.get("src/main/resources/cleancode/studycafe/locker.csv"));
            for (String line : lines) {
                String[] values = line.split(",");

                LockerTicket lockerTicket = LockerTicket.of(
                    Type.valueOf(values[0]),
                    Integer.parseInt(values[1]),
                    Money.of(Integer.parseInt(values[2]))
                );

                this.lockerTickets = lockerTickets.add(lockerTicket);
            }
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }

    }

    @Override
    public LockerTickets findAll() {
        return lockerTickets;
    }

    @Override
    public LockerTicket findByStudyCafeTicket(Ticket selectedTicket) {
        return findAll()
                .filterByType(selectedTicket.getType())
                .filterByDuration(selectedTicket.getDuration())
                .findFirstOrElseNull();
    }
}

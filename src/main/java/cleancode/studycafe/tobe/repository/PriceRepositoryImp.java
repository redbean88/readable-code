package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.StudyCafeTicket;
import cleancode.studycafe.tobe.model.Type;
import cleancode.studycafe.tobe.vo.DiscountRate;
import cleancode.studycafe.tobe.vo.Money;
import cleancode.studycafe.tobe.model.StudyCafeTickets;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PriceRepositoryImp implements PriceRepository {

    private StudyCafeTickets studyCafeTickets = StudyCafeTickets.of();

    public PriceRepositoryImp() {
        try {
            List<String> lines = Files.readAllLines(
                Paths.get("src/main/resources/cleancode/studycafe/pass-list.csv"));
            for (String line : lines) {
                String[] values = line.split(",");
                StudyCafeTicket studyCafeTicket = StudyCafeTicket.of(
                    Type.valueOf(values[0]),
                    Integer.parseInt(values[1]),
                    Money.of(Integer.parseInt(values[2])),
                    DiscountRate.of(Double.parseDouble(values[3]))
                );

                this.studyCafeTickets = studyCafeTickets.add(studyCafeTicket);
            }
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }

    }

    @Override
    public StudyCafeTickets findAll() {
        return studyCafeTickets;
    }

    @Override
    public StudyCafeTickets findByTickets(Type type) {
        return findAll()
            .filterByType(type);
    }
}

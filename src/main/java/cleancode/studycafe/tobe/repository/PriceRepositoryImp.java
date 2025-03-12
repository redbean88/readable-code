package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.Ticket;
import cleancode.studycafe.tobe.model.Type;
import cleancode.studycafe.tobe.vo.DiscountRate;
import cleancode.studycafe.tobe.vo.Money;
import cleancode.studycafe.tobe.model.Tickets;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PriceRepositoryImp implements PriceRepository {

    private Tickets tickets = Tickets.of();

    public PriceRepositoryImp() {
        try {
            List<String> lines = Files.readAllLines(
                Paths.get("src/main/resources/cleancode/studycafe/pass-list.csv"));
            for (String line : lines) {
                String[] values = line.split(",");
                Ticket ticket = Ticket.of(
                    Type.valueOf(values[0]),
                    Integer.parseInt(values[1]),
                    Money.of(Integer.parseInt(values[2])),
                    DiscountRate.of(Double.parseDouble(values[3]))
                );

                this.tickets = tickets.add(ticket);
            }
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }

    }

    @Override
    public Tickets findAll() {
        return tickets;
    }

    @Override
    public Tickets findByType(Type type) {
        return findAll()
            .filterByType(type);
    }
}

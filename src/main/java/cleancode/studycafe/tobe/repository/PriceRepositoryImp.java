package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.StudyCafeTicket;
import cleancode.studycafe.tobe.model.Type;
import cleancode.studycafe.tobe.vo.DiscountRate;
import cleancode.studycafe.tobe.vo.Money;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PriceRepositoryImp implements PriceRepository {

    private static final List<StudyCafeTicket> STUDY_CAFE_TICKETS = new ArrayList<>();

    public PriceRepositoryImp() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/cleancode/studycafe/pass-list.csv"));
            for (String line : lines) {
                String[] values = line.split(",");
                Type type = Type.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);
                Money money = Money.of(price);
                double dRate = Double.parseDouble(values[3]);
                DiscountRate discountRate = DiscountRate.of(dRate);

                StudyCafeTicket studyCafeTicket = StudyCafeTicket.of(type, duration, money, discountRate);
                STUDY_CAFE_TICKETS.add(studyCafeTicket);
            }
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }

    }

    @Override
    public List<StudyCafeTicket> findAll() {
        return STUDY_CAFE_TICKETS;
    }

    @Override
    public List<StudyCafeTicket> findByTickets(Type type) {
        return STUDY_CAFE_TICKETS.stream()
            .filter(studyCafePass -> studyCafePass.getTicket() == type)
            .toList();
    }
}

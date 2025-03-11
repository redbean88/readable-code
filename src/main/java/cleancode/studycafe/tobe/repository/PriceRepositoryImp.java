package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.Ticket;
import cleancode.studycafe.tobe.vo.Money;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PriceRepositoryImp implements PriceRepository {

    private static final List<StudyCafePass> STUDY_CAFE_PASSES = new ArrayList<>();

    public PriceRepositoryImp() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/cleancode/studycafe/pass-list.csv"));
            for (String line : lines) {
                String[] values = line.split(",");
                Ticket ticket = Ticket.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);
                Money money = Money.of(price);
                double discountRate = Double.parseDouble(values[3]);

                StudyCafePass studyCafePass = StudyCafePass.of(ticket, duration, money, discountRate);
                STUDY_CAFE_PASSES.add(studyCafePass);
            }
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }

    }

    @Override
    public List<StudyCafePass> findAll() {
        return STUDY_CAFE_PASSES;
    }

    @Override
    public List<StudyCafePass> findByTicket(Ticket ticket) {
        return STUDY_CAFE_PASSES.stream()
            .filter(studyCafePass -> studyCafePass.getPassType() == ticket)
            .toList();
    }
}

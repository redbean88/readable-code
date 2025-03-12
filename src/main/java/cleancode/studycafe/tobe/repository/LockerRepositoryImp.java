package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.Type;
import cleancode.studycafe.tobe.vo.Money;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LockerRepositoryImp implements LockerRepository {

    public static final List<StudyCafeLockerPass> LOCKER_PASSES = new ArrayList<>();
    ;

    public LockerRepositoryImp() {
        try {
            List<String> lines = Files.readAllLines(
                Paths.get("src/main/resources/cleancode/studycafe/locker.csv"));
            for (String line : lines) {
                String[] values = line.split(",");
                Type type = Type.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);
                Money money = Money.of(price);

                StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(type, duration, money);
                LOCKER_PASSES.add(lockerPass);
            }
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }

    }

    @Override
    public List<StudyCafeLockerPass> findAll() {
        return LOCKER_PASSES;
    }

    @Override
    public StudyCafeLockerPass findByStateAndDuration(Type type, int duration) {
        return LOCKER_PASSES.stream()
            .filter(option ->
                option.getPassType() == type && option.getDuration() == duration
            )
            .findFirst()
            .orElse(null);
    }
}

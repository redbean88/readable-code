package cleancode.studycafe.tobe.infra.io;

import cleancode.studycafe.tobe.infra.exception.AppException;
import cleancode.studycafe.tobe.model.StudyCafeTicket;
import cleancode.studycafe.tobe.model.Type;

import java.util.List;
import java.util.Scanner;

public class InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    public Type getTicketSelectedByUser() {
        String userInput = SCANNER.nextLine();

        if ("1".equals(userInput)) {
            return Type.HOURLY;
        }
        if ("2".equals(userInput)) {
            return Type.WEEKLY;
        }
        if ("3".equals(userInput)) {
            return Type.FIXED;
        }
        throw new AppException("잘못된 입력입니다.");
    }

    public StudyCafeTicket getSelectPass(List<StudyCafeTicket> passes) {
        String userInput = SCANNER.nextLine();
        int selectedIndex = Integer.parseInt(userInput) - 1;
        return passes.get(selectedIndex);
    }

    public boolean getLockerSelection() {
        String userInput = SCANNER.nextLine();
        return "1".equals(userInput);
    }

}

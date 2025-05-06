package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import java.util.List;
import java.util.Scanner;

public class InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    public StudyCafePassType getPassTypeBy() {
        String userInput = getUserInput();
        return getPassTypeBy(userInput);
    }


    public StudyCafePassType getPassTypeBy(String userInput) {

        if ("1".equals(userInput)) {
            return StudyCafePassType.HOURLY;
        }
        if ("2".equals(userInput)) {
            return StudyCafePassType.WEEKLY;
        }
        if ("3".equals(userInput)) {
            return StudyCafePassType.FIXED;
        }
        throw new AppException("잘못된 입력입니다.");
    }

    private String getUserInput() {
        return SCANNER.nextLine();
    }

    public StudyCafePass getSelectPass(List<StudyCafePass> passes, String userInput) {
        int selectedIndex = Integer.parseInt(userInput) - 1;
        if(selectedIndex < passes.size()) {
            return passes.get(selectedIndex);
        }
        throw new AppException("잘못된 입력입니다.");
    }

    public boolean getLockerSelection() {
        String userInput = getUserInput();
        return getLockerSelection(userInput);
    }


    public boolean getLockerSelection(String userInput) {
        return "1".equals(userInput);
    }

    public StudyCafePass getSelectPass(List<StudyCafePass> hourlyPasses) {
        String userInput = getUserInput();
        return getSelectPass(hourlyPasses, userInput);
    }
}

package cleancode.studycafe.tobe.vo;

import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafeTicket;

public class Result {

    private final StudyCafeTicket selectedPass;
    private final StudyCafeLockerPass lockerPass;

    private Result(StudyCafeTicket selectedPass, StudyCafeLockerPass lockerPass) {
        this.selectedPass = selectedPass;
        this.lockerPass = lockerPass;
    }

    public static Result of(StudyCafeTicket selectedPass, StudyCafeLockerPass pass) {
        return new Result(selectedPass, pass);
    }

    public static Result of(StudyCafeTicket selectedPass) {
        return new Result(selectedPass, null);
    }

    public String showSelectedPass() {
        return selectedPass.display();
    }

    public boolean hasLockerPass() {
        return lockerPass != null;
    }

    public String showLockerPass() {
        return lockerPass.display();
    }

    public Money calculateDiscountPrice() {
        return selectedPass.calculateDiscountPrice();
    }

    public Money calculateTotalPrice() {
        return selectedPass
            .getPrice()
            .subtract(calculateDiscountPrice())
            .add(lockerPass != null ? lockerPass.getPrice() : Money.ofZero());
    }
}

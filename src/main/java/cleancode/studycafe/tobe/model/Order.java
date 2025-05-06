package cleancode.studycafe.tobe.model;

import java.util.Optional;

public class Order {

    private final StudyCafeLockerPass studyCafeLockerPass;
    private final StudyCafePass studyCafePass;

    private Order(StudyCafePass studyCafePass, StudyCafeLockerPass studyCafeLockerPass) {
        this.studyCafeLockerPass = studyCafeLockerPass;
        this.studyCafePass = studyCafePass;
    }
    private Order(StudyCafePass studyCafePass) {
        this.studyCafeLockerPass = null;
        this.studyCafePass = studyCafePass;
    }

    public static Order of(StudyCafePass studyCafePass, StudyCafeLockerPass studyCafeLockerPass) {
        return new Order(studyCafePass, studyCafeLockerPass);
    }

    public static Order of(StudyCafePass studyCafePass) {
        return new Order(studyCafePass);
    }

    public Optional<StudyCafeLockerPass> getStudyCafeLockerPass() {
        return Optional.ofNullable(studyCafeLockerPass);
    }

    public StudyCafePass getStudyCafePass() {
        return studyCafePass;
    }

    public int getDiscountPrice() {
        return studyCafePass.getDiscountPrice();
    }

    public int getTotalPrice() {
        Integer lockerPrice = Optional.ofNullable(studyCafeLockerPass).map(StudyCafeLockerPass::getPrice).orElse(0);
        return studyCafePass.getPrice() - getDiscountPrice() + lockerPrice;
    }
}

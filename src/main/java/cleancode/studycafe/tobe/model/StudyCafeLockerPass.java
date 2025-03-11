package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.vo.Money;

public class StudyCafeLockerPass {

    private final StudyCafePassType passType;
    private final int duration;
    private final Money money;

    private StudyCafeLockerPass(StudyCafePassType passType, int duration, Money money) {
        this.passType = passType;
        this.duration = duration;
        this.money = money;
    }

    public static StudyCafeLockerPass of(StudyCafePassType passType, int duration, Money money) {
        return new StudyCafeLockerPass(passType, duration, money);
    }

    public StudyCafePassType getPassType() {
        return passType;
    }

    public int getDuration() {
        return duration;
    }

    public Money getPrice() {
        return money;
    }

    public String display() {
        if (passType == StudyCafePassType.HOURLY) {
            return String.format("%s시간권 - %s원", duration, money);
        }
        if (passType == StudyCafePassType.WEEKLY) {
            return String.format("%s주권 - %s원", duration, money);
        }
        if (passType == StudyCafePassType.FIXED) {
            return String.format("%s주권 - %s원", duration, money);
        }
        return "";
    }

}

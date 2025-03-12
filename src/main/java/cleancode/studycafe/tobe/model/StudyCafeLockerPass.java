package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.vo.Money;

public class StudyCafeLockerPass {

    private final Type passType;
    private final int duration;
    private final Money money;

    private StudyCafeLockerPass(Type passType, int duration, Money money) {
        this.passType = passType;
        this.duration = duration;
        this.money = money;
    }

    public static StudyCafeLockerPass of(Type passType, int duration, Money money) {
        return new StudyCafeLockerPass(passType, duration, money);
    }

    public Type getPassType() {
        return passType;
    }

    public int getDuration() {
        return duration;
    }

    public Money getPrice() {
        return money;
    }

    public String display() {
        if (passType == Type.HOURLY) {
            return String.format("%s시간권 - %s원", duration, money);
        }
        if (passType == Type.WEEKLY) {
            return String.format("%s주권 - %s원", duration, money);
        }
        if (passType == Type.FIXED) {
            return String.format("%s주권 - %s원", duration, money);
        }
        return "";
    }

}

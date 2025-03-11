package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.vo.Money;

public class StudyCafePass {

    private final StudyCafePassType passType;
    private final int duration;
    private final Money money;
    private final double discountRate;

    private StudyCafePass(StudyCafePassType passType, int duration, Money money, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.money = money;
        this.discountRate = discountRate;
    }

    public static StudyCafePass of(StudyCafePassType passType, int duration, Money money, double discountRate) {
        return new StudyCafePass(passType, duration, money, discountRate);
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

    public double getDiscountRate() {
        return discountRate;
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

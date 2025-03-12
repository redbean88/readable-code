package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.utils.Calculator;
import cleancode.studycafe.tobe.vo.DiscountRate;
import cleancode.studycafe.tobe.vo.Money;

public class StudyCafeTicket {

    private final Type type;
    private final int duration;
    private final Money money;
    private final DiscountRate discountRate;

    private StudyCafeTicket(Type type, int duration, Money money, DiscountRate discountRate) {
        this.type = type;
        this.duration = duration;
        this.money = money;
        this.discountRate = discountRate;
    }

    public static StudyCafeTicket of(Type passType) {
        return new StudyCafeTicket(passType, 0, Money.ofZero(), DiscountRate.ofOne());
    }


    public static StudyCafeTicket of(Type passType, int duration, Money money, DiscountRate discountRate) {
        return new StudyCafeTicket(passType, duration, money, discountRate);
    }

    public Type getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public Money getPrice() {
        return money;
    }

    public DiscountRate getDiscountRate() {
        return discountRate;
    }

    public Money calculateDiscountPrice() {
        return Calculator.increaseBy(money, discountRate);
    }

    public String display() {
        if (type == Type.HOURLY) {
            return String.format("%s시간권 - %s원", duration, money);
        }
        if (type == Type.WEEKLY) {
            return String.format("%s주권 - %s원", duration, money);
        }
        if (type == Type.FIXED) {
            return String.format("%s주권 - %s원", duration, money);
        }
        return "";
    }

}

package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.utils.Calculator;
import cleancode.studycafe.tobe.vo.DiscountRate;
import cleancode.studycafe.tobe.vo.Money;

public class Ticket {

    private final Type type;
    private final int duration;
    private final Money money;
    private final DiscountRate discountRate;

    private Ticket(Type type, int duration, Money money, DiscountRate discountRate) {
        this.type = type;
        this.duration = duration;
        this.money = money;
        this.discountRate = discountRate;
    }

    public static Ticket of(Type type) {
        return new Ticket(type, 0, Money.ofZero(), DiscountRate.ofOne());
    }


    public static Ticket of(Type type, int duration, Money money, DiscountRate discountRate) {
        return new Ticket(type, duration, money, discountRate);
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


    public Money calculateDiscountPrice() {
        return Calculator.increaseBy(money, discountRate);
    }


    public String display() {
        return switch (type) {
            case HOURLY -> String.format("%s시간권 - %s원", duration, money);
            case WEEKLY -> String.format("%s주권 - %s원", duration, money);
            case FIXED -> String.format("%s주권 - %s원", duration, money);
        };
    }

}

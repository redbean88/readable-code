package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.vo.Money;

public class LockerTicket {

    private final Type type;
    private final int duration;
    private final Money money;

    private LockerTicket(Type type, int duration, Money money) {
        this.type = type;
        this.duration = duration;
        this.money = money;
    }

    public static LockerTicket of(Type type, int duration, Money money) {
        return new LockerTicket(type, duration, money);
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

    public String display() {
        return switch (type) {
            case HOURLY -> String.format("%s시간권 - %s원", duration, money);
            case WEEKLY -> String.format("%s주권 - %s원", duration, money);
            case FIXED -> String.format("%s주권 - %s원", duration, money);
        };
    }

}

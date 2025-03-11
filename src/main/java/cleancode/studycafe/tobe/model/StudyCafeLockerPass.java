package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.vo.Money;

public class StudyCafeLockerPass {

    private final Ticket passType;
    private final int duration;
    private final Money money;

    private StudyCafeLockerPass(Ticket passType, int duration, Money money) {
        this.passType = passType;
        this.duration = duration;
        this.money = money;
    }

    public static StudyCafeLockerPass of(Ticket passType, int duration, Money money) {
        return new StudyCafeLockerPass(passType, duration, money);
    }

    public Ticket getPassType() {
        return passType;
    }

    public int getDuration() {
        return duration;
    }

    public Money getPrice() {
        return money;
    }

    public String display() {
        if (passType == Ticket.시간단위이용권) {
            return String.format("%s시간권 - %s원", duration, money);
        }
        if (passType == Ticket.주단위이용권) {
            return String.format("%s주권 - %s원", duration, money);
        }
        if (passType == Ticket.일인고정석) {
            return String.format("%s주권 - %s원", duration, money);
        }
        return "";
    }

}

package cleancode.studycafe.tobe.vo;

import java.text.DecimalFormat;
import java.util.Objects;

public class Money {
    private double amount;

    private Money(double amount) {
        this.amount = amount;
    }

    public static Money of(double amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("금액은 0보다 작을 수 없습니다.");
        }
        return new Money(amount);
    }

    public static Money ofZero() {
        return new Money(0);
    }

    public Money add(Money money) {
        return new Money(amount + money.amount);
    }

    public Money subtract(Money money) {
        return new Money(amount - money.amount);
    }

    public Money multiply(double rate) {
        return new Money(amount * rate);
    }



    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Money money)) {
            return false;
        }
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(amount);
    }
}

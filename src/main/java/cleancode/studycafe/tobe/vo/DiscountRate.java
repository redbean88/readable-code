package cleancode.studycafe.tobe.vo;

import java.util.Objects;

public class DiscountRate implements Rate {

    private final double rate;

    private DiscountRate(double rate) {
        this.rate = rate;
    }

    public static DiscountRate of(double rate) {
        return new DiscountRate(rate);
    }

    public static DiscountRate ofOne() {
        return new DiscountRate(1);
    }

    public double getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DiscountRate that)) {
            return false;
        }
        return Double.compare(rate, that.rate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rate);
    }

}

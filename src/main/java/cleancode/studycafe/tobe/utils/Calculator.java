package cleancode.studycafe.tobe.utils;

import cleancode.studycafe.tobe.vo.Money;
import cleancode.studycafe.tobe.vo.Rate;

public class Calculator {

    public static Money increaseBy(Money money, Rate rate) {
        return money.multiply(rate.getRate());
    }
}

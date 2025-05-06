package cleancode.studycafe.tobe.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderTest {

    @DisplayName("이주권 미만은 할인되지 않은 금액으로 계산된다.")
    @ParameterizedTest
    @CsvSource(value = {
        "HOURLY,2,4000,0.0",
        "HOURLY,4,6500,0.0",
        "HOURLY,6,9000,0.0",
        "HOURLY,8,11000,0.0",
        "HOURLY,10,12000,0.0",
        "HOURLY,12,13000,0.0",
        "WEEKLY,1,60000,0.0"
    })
    void 이주권_미만은_할인되지_않은_금액으로_계산된다(StudyCafePassType passType, int duration, int price, double discountRate) {
        // given
        StudyCafePass selectPass = StudyCafePass.of(passType, duration, price, discountRate);

        // when
        Order order = Order.of(selectPass);

        // then
        assertThat(order.getDiscountPrice()).isZero();
        assertThat(order.getTotalPrice()).isEqualTo(price);
    }

    @DisplayName("이주권 이상은 12주권 미만은 할인된 금액으로 계산된다.")
    @ParameterizedTest
    @CsvSource(value = {
        "WEEKLY,2,100000,0.1",
        "WEEKLY,3,130000,0.1",
        "WEEKLY,4,150000,0.1",
        "FIXED,4,250000,0.1",
        "FIXED,12,700000,0.15"
    })
    void 이주권_이상은_12주권_미만은_할인된_금액으로_계산된다(StudyCafePassType passType, int duration, int price, double discountRate) {
        // given
        StudyCafePass selectPass = StudyCafePass.of(passType, duration, price, discountRate);

        // when
        Order order = Order.of(selectPass);

        // then
        assertThat(order.getDiscountPrice()).isNotZero();
        assertThat(order.getTotalPrice()).isEqualTo(price - order.getDiscountPrice());
    }


    @DisplayName("고정석에 사물함을 사용하는 경우")
    @ParameterizedTest
    @CsvSource(value = {
        "FIXED,4,250000,0.1,10000",
        "FIXED,12,700000,0.15,30000"
    })
    void 고정석에_사물함을_사용하는_경우(StudyCafePassType passType, int duration, int price, double discountRate, int lockerPrice) {
        // given
        StudyCafePass selectPass = StudyCafePass.of(passType, duration, price, discountRate);
        StudyCafeLockerPass studyCafeLockerPass = StudyCafeLockerPass.of(passType, duration, lockerPrice);

        // when
        Order order = Order.of(selectPass, studyCafeLockerPass);

        // then
        assertThat(order.getDiscountPrice()).isNotZero();
        assertThat(order.getTotalPrice()).isEqualTo(price - order.getDiscountPrice() + lockerPrice);
    }

}
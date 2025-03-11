package cleancode.studycafe.tobe.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @DisplayName("생성테스트")
    @Test
    void MoneyTest() {
        // given
        Money money = Money.of(1000);
        // when

        assertThat(money).isEqualTo(Money.of(1000));
        assertThat(money.toString()).isEqualTo("1,000");
        // then
    }

}
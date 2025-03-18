package cleancode.minesweeper.tobe.minesweeper.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameExceptionTest {

    @DisplayName("게임 관련 예외가 생성된다")
    @Test
    void 게임_관련_예외가_생성된다() {
        // given
        GameException gameException = new GameException("테스트");
        // when
        // then
        assertThat(gameException).isInstanceOf(GameException.class);
    }

}
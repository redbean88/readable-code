package cleancode.minesweeper.tobe.minesweeper.gamelevel;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameLevelTest {

    @DisplayName("버전별 난이도를 생성한다")
    @ParameterizedTest
    @MethodSource("provideGameLevel")
    void createVeryBeginnerLevel(GameLevel gameLevel, int colSize, int rowSize, int landMines) {
        // given
        // when
        // then
        assertThat(gameLevel).isNotNull();
        assertThat(gameLevel.getColSize()).isEqualTo(colSize);
        assertThat(gameLevel.getRowSize()).isEqualTo(rowSize);
        assertThat(gameLevel.getLandMineCount()).isEqualTo(landMines);
    }

    private static Stream<Arguments> provideGameLevel() {
        return Stream.of(
            Arguments.of(new VeryBeginner(), 5, 4, 2),
            Arguments.of(new Beginner(), 10, 8, 10),
            Arguments.of(new Middle(), 18, 14, 40),
            Arguments.of(new Advanced(), 24, 20, 99)
        );
    }
}

/**
 * 해피 케이스 예외 케이스 경계값 테스트 ↪ 범위(이상, 이하, 초과, 미만), 구간, 날짜 등
 */
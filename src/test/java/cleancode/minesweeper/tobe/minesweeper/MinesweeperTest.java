package cleancode.minesweeper.tobe.minesweeper;

import static org.assertj.core.api.Assertions.assertThat;

import cleancode.minesweeper.tobe.minesweeper.config.GameConfig;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.Beginner;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleOutputHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MinesweeperTest {

    @DisplayName("지뢰찾기 게임을 생성한다")
    @Test
    void 지뢰찾기_게임을_생성한다() {
        // given
        Beginner gameLevel = new Beginner();
        ConsoleInputHandler inputHandler = new ConsoleInputHandler();
        ConsoleOutputHandler outputHandler = new ConsoleOutputHandler();
        GameConfig gameConfig = new GameConfig(
            gameLevel,
            inputHandler,
            outputHandler
        );

        // when
        Minesweeper minesweeper = new Minesweeper(gameConfig);
        minesweeper.initialize();

        // then
        assertThat(minesweeper).isNotNull();
    }

}
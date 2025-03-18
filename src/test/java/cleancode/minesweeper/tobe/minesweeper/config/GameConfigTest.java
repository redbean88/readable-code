package cleancode.minesweeper.tobe.minesweeper.config;

import static org.assertj.core.api.Assertions.assertThat;

import cleancode.minesweeper.tobe.minesweeper.gamelevel.Beginner;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleOutputHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameConfigTest {

    @DisplayName("게임 설정을 생성 할 수 있다.")
    @Test
    void 게임_설정을_생성_할_수_있다() {
        // given
        Beginner gameLevel = new Beginner();
        ConsoleInputHandler inputHandler = new ConsoleInputHandler();
        ConsoleOutputHandler outputHandler = new ConsoleOutputHandler();
        // when
        GameConfig gameConfig = new GameConfig(
            gameLevel,
            inputHandler,
            outputHandler
        );

        // then
        assertThat(gameConfig.getGameLevel()).isEqualTo(gameLevel);
        assertThat(gameConfig.getInputHandler()).isEqualTo(inputHandler);
        assertThat(gameConfig.getOutputHandler()).isEqualTo(outputHandler);
    }
}
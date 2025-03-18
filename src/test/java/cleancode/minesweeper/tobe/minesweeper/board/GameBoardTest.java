package cleancode.minesweeper.tobe.minesweeper.board;

import static org.assertj.core.api.Assertions.assertThat;

import cleancode.minesweeper.tobe.minesweeper.gamelevel.Advanced;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.GameLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameBoardTest {

    @DisplayName("게임 보드를 생성한다.")
    @Test
    void 게임_보드를_생성한다() {
        // given
        GameLevel advanced = new Advanced();

        // when
        GameBoard gameBoard = new GameBoard(advanced);
        gameBoard.initializeGame();

        // then
        assertThat(gameBoard).isNotNull();
        assertThat(gameBoard.getColSize()).isEqualTo(24);
        assertThat(gameBoard.getRowSize()).isEqualTo(20);
    }

    @DisplayName("게임 상태를 확인한다.")
    @Test
    void 게임_상태를_확인한다() {
        // given
        GameLevel advanced = new Advanced();

        // when
        GameBoard gameBoard = new GameBoard(advanced);
        gameBoard.initializeGame();
        boolean inProgress = gameBoard.isInProgress();
        boolean loseStatus = gameBoard.isLoseStatus();
        boolean winStatus = gameBoard.isWinStatus();

        // then
        assertThat(gameBoard).isNotNull();
        assertThat(inProgress).isTrue();
        assertThat(loseStatus).isFalse();
        assertThat(winStatus).isFalse();
    }


}
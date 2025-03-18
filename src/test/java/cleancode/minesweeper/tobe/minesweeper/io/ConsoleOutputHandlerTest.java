package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.board.GameBoard;
import cleancode.minesweeper.tobe.minesweeper.exception.GameException;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.Advanced;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.GameLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConsoleOutputHandlerTest {

    @DisplayName("호출을 정상적으로 할 수 있다")
    @Test
    void 호출을_정상적으로_할_수_있다() {
        // given
        OutputHandler consoleOutputHandler = new ConsoleOutputHandler();
        GameLevel advanced = new Advanced();
        GameBoard gameBoard = new GameBoard(advanced);
        gameBoard.initializeGame();
        // when
        // then
        consoleOutputHandler.showBoard(gameBoard);
        consoleOutputHandler.showGameStartComments();
        consoleOutputHandler.showGameWinningComment();
        consoleOutputHandler.showGameLosingComment();
        consoleOutputHandler.showCommentForUserAction();
        consoleOutputHandler.showCommentForSelectingCell();
        consoleOutputHandler.showExceptionMessage(new GameException("테스트"));
        consoleOutputHandler.showSimpleMessage("테스트");
    }

}
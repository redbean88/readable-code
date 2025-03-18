package cleancode.minesweeper.tobe.minesweeper.board.position;

import static org.assertj.core.api.Assertions.assertThat;

import cleancode.minesweeper.tobe.minesweeper.board.cell.Cell;
import cleancode.minesweeper.tobe.minesweeper.board.cell.EmptyCell;
import cleancode.minesweeper.tobe.minesweeper.board.cell.LandMineCell;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CellPositionsTest {

    @DisplayName("일급 컬렉션을 생성한다.")
    @Test
    void 일급_컬렉션을_생성한다() {
        // given
        CellPosition cellPosition = CellPosition.of(1, 1);
        CellPosition cellPosition2 = CellPosition.of(2, 1);
        // when
        CellPositions cellPositions = CellPositions.of(Arrays.asList(cellPosition, cellPosition2));
        // then
        assertThat(cellPositions).isNotNull();
    }

    @DisplayName("일급 컬렉션에서 일부 항목을 제거한다.")
    @Test
    void 일급_컬렉션에서_일부_항목을_제거한다() {
        // given
        CellPosition cellPosition = CellPosition.of(1, 1);
        CellPosition cellPosition2 = CellPosition.of(2, 1);
        // when
        CellPositions cellPositions = CellPositions.of(Arrays.asList(cellPosition, cellPosition2));
        List<CellPosition> subtracted = cellPositions.subtract(List.of(cellPosition2));
        // then
        assertThat(subtracted)
            .isNotNull()
            .hasSize(1)
            .containsExactly(cellPosition)
            .doesNotContain(cellPosition2);
    }

    @DisplayName("보드의 값을 일급 컬렉션으로 변경한다.")
    @Test
    void 보드의_값을_일급_컬렉션으로_변경한다() {
        // given
        Cell[][] board = new Cell[2][2];
        board[0][0] = new EmptyCell();
        board[0][1] = new EmptyCell();
        board[1][0] = new EmptyCell();
        board[1][1] = new LandMineCell();
        // when
        CellPositions cellPositions = CellPositions.from(board);

        // then
        assertThat(cellPositions).isNotNull();

    }

    @DisplayName("일급 컬렉션을 리스트로 변경한다.")
    @Test
    void 일급_컬렉션을_리스트로_변경한다() {
        // given
        Cell[][] board = new Cell[2][2];
        board[0][0] = new EmptyCell();
        board[0][1] = new EmptyCell();
        board[1][0] = new EmptyCell();
        board[1][1] = new LandMineCell();
        // when
        CellPositions cellPositions = CellPositions.from(board);
        List<CellPosition> positions = cellPositions.getPositions();

        // then
        assertThat(positions).isNotNull()
            .hasSize(4);
    }

    @DisplayName("특정 갯수의 셀 리스트를 반환한다.")
    @Test
    void 특정_갯수의_셀_리스트를_반환한다() {
        // given
        Cell[][] board = new Cell[2][2];
        board[0][0] = new EmptyCell();
        board[0][1] = new EmptyCell();
        board[1][0] = new EmptyCell();
        board[1][1] = new LandMineCell();
        // when
        CellPositions cellPositions = CellPositions.from(board);
        List<CellPosition> extractedRandomPositions = cellPositions.extractRandomPositions(2);

        // then
        assertThat(extractedRandomPositions).isNotNull()
                .hasSize(2);
    }

}
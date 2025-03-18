package cleancode.minesweeper.tobe.minesweeper.board.cell;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CellsTest {

    @DisplayName("Cell 리스트를 Cells로 변환한다.")
    @Test
    void cellListToCells() {
        // given
        List<Cell> cellList = new ArrayList<>();
        // when
        Cells cells = Cells.of(cellList);
        // then
        assertThat(cells).isNotNull();
    }

    @DisplayName("모든 셀이 확인된 상태이다.")
    @Test
    void 모든_셀이_확인된_상태이다() {
        // given
        EmptyCell emptyCell = new EmptyCell();
        emptyCell.open();
        EmptyCell emptyCell2 = new EmptyCell();
        emptyCell2.open();

        // when
        Cells cells = Cells.of(Arrays.asList(emptyCell, emptyCell2));
        // then
        assertThat(cells).isNotNull();
        assertThat(cells.isAllChecked()).isTrue();
    }

}
package cleancode.minesweeper.tobe.minesweeper.board.cell;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberCellTest {

    @DisplayName("깃발로 표시되어 있는지 확인한다.")
    @Test
    void 깃발로_표시되어_있는지_확인한다() {
        // given
        NumberCell numberCell = new NumberCell(4);
        // when
        numberCell.flag();

        // then
        CellSnapshot snapshot = numberCell.getSnapshot();
        assertThat(snapshot.getStatus()).isEqualTo(CellSnapshotStatus.FLAG);
    }

    @DisplayName("숫자를 선택하였는지 확인한다.")
    @Test
    void 숫자를_선택하였는지_확인한다() {
        // given
        NumberCell numberCell = new NumberCell(4);
        // when
        numberCell.open();

        // then
        CellSnapshot snapshot = numberCell.getSnapshot();
        assertThat(snapshot.getStatus()).isEqualTo(CellSnapshotStatus.NUMBER);
    }


}
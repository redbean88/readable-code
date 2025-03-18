package cleancode.minesweeper.tobe.minesweeper.board.cell;

import static org.assertj.core.api.Assertions.assertThat;

import cleancode.minesweeper.tobe.minesweeper.io.sign.CellSignProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LandMineCellTest {

    @DisplayName("깃발로 표시되어 있는지 확인한다.")
    @Test
    void 깃발로_표시되어_있는지_확인한다() {
        // given
        LandMineCell landMineCell = new LandMineCell();
        // when
        landMineCell.flag();

        // then
        CellSnapshot snapshot = landMineCell.getSnapshot();
        assertThat(snapshot.getStatus()).isEqualTo(CellSnapshotStatus.FLAG);
    }

    @DisplayName("지뢰를 선택하였는지 확인한다.")
    @Test
    void 지뢰를_선택하였는지_확인한다() {
        // given
        LandMineCell landMineCell = new LandMineCell();
        // when
        landMineCell.open();

        // then
        CellSnapshot snapshot = landMineCell.getSnapshot();
        assertThat(snapshot.getStatus()).isEqualTo(CellSnapshotStatus.LAND_MINE);
    }

    @DisplayName("각 표기를 확인한다.")
    @Test
    void 각_표기를_확인한다() {
        // given
        // when

        // then
        CellSnapshot empty = CellSnapshot.ofEmpty();
        CellSnapshot flag = CellSnapshot.ofFlag();
        CellSnapshot mine = CellSnapshot.ofLandMine();
        CellSnapshot unchecked = CellSnapshot.ofUnchecked();
        CellSnapshot number = CellSnapshot.ofNumber(0);
        assertThat(CellSignProvider.EMPTY.provide(empty)).isEqualTo("■");
        assertThat(CellSignProvider.FLAG.provide(flag)).isEqualTo("⚑");
        assertThat(CellSignProvider.LAND_MINE.provide(mine)).isEqualTo("☼");
        assertThat(CellSignProvider.UNCHECKED.provide(unchecked)).isEqualTo("□");
        assertThat(CellSignProvider.NUMBER.provide(number)).isEqualTo("0");
    }

}
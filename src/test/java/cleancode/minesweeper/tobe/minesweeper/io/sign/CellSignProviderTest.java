package cleancode.minesweeper.tobe.minesweeper.io.sign;

import static org.assertj.core.api.Assertions.assertThat;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CellSignProviderTest {

    @DisplayName("셀 상태별 셀무늬를 제공받는다.")
    @Test
    void 셀_상태별_셀무늬를_제공받는다() {
        // given
        CellSnapshot mine = CellSnapshot.ofLandMine();
        // when

        String cellSignFrom = CellSignProvider.findCellSignFrom(mine);
        String provide = CellSignProvider.LAND_MINE.provide(mine);
        // then
        assertThat(cellSignFrom).isEqualTo("☼")
            .isEqualTo(provide);

    }

}
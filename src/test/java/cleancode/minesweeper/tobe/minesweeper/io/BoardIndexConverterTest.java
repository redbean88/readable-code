package cleancode.minesweeper.tobe.minesweeper.io;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardIndexConverterTest {

    @DisplayName("선택한 row String을 int로 변경한다.")
    @Test
    void 선택한_row_String을_int로_변경한다() {
        // given
        String row = "a1";
        // when

        int selectedColIndex = new BoardIndexConverter().getSelectedRowIndex(row);
        // then
        assertThat(selectedColIndex).isZero();
    }

    @DisplayName("선택한 col String을 int로 변경한다.")
    @Test
    void 선택한_col_String을_int로_변경한다() {
        // given
        String row = "a1";
        // when

        int selectedColIndex = new BoardIndexConverter().getSelectedColIndex(row);
        // then
        assertThat(selectedColIndex).isZero();
    }

}
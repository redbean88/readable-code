package cleancode.minesweeper.tobe.minesweeper.board.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CellPositionTest {


    @DisplayName("셀 위치를 생성한다.")
    @Test
    void 셀_위치를_생성한다() {
        // given
        int rowIndex = 0;
        int colIndex = 0;
        // when
        CellPosition cellPosition = CellPosition.of(rowIndex, colIndex);
        // then
        assertThat(cellPosition).isNotNull();
        assertThat(cellPosition.getRowIndex()).isEqualTo(rowIndex);
        assertThat(cellPosition.getColIndex()).isEqualTo(colIndex);
    }


    @DisplayName("위치는 자연수만 입력 가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"0,-1","-1,0"})
    void 위치는_자연수만_입력_가능하다(int rowIndex, int colIndex) {
        // given
        // when
        // then
        assertThatThrownBy(() -> CellPosition.of(rowIndex, colIndex))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("올바르지 않은 좌표입니다.");
    }

    @DisplayName("동일한 값을 비교한다.")
    @Test
    void 동일한_값을_비교한다() {
        // given
        int rowIndex = 0;
        int colIndex = 0;
        // when
        CellPosition cellPosition = CellPosition.of(rowIndex, colIndex);
        CellPosition cellPosition2 = CellPosition.of(rowIndex, colIndex);
        // then
        assertThat(cellPosition).isEqualTo(cellPosition2);
        assertThat(cellPosition.hashCode()).hasSameHashCodeAs(cellPosition2.hashCode());
    }

    @DisplayName("row 값을 비교한다.")
    @Test
    void row_값을_비교한다(){
        // given
        int rowIndex = 1;
        int colIndex = 0;
        // when
        CellPosition cellPosition = CellPosition.of(rowIndex, colIndex);
        // then
        assertThat(cellPosition.isRowIndexMoreThanOrEqual(1)).isTrue();
        assertThat(cellPosition.isRowIndexMoreThanOrEqual(0)).isTrue();
        assertThat(cellPosition.isRowIndexLessThan(1)).isFalse();
        assertThat(cellPosition.isRowIndexLessThan(0)).isFalse();
    }

    @DisplayName("col 값을 비교한다.")
    @Test
    void col_값을_비교한다(){
        // given
        int rowIndex = 0;
        int colIndex = 1;
        // when
        CellPosition cellPosition = CellPosition.of(rowIndex, colIndex);
        // then
        assertThat(cellPosition.isColIndexMoreThanOrEqual(1)).isTrue();
        assertThat(cellPosition.isColIndexMoreThanOrEqual(0)).isTrue();
        assertThat(cellPosition.isColIndexLessThan(1)).isFalse();
        assertThat(cellPosition.isColIndexLessThan(0)).isFalse();
    }

}
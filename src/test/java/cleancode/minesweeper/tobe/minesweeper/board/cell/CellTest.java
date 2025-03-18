package cleancode.minesweeper.tobe.minesweeper.board.cell;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CellTest {

    public static Stream<Arguments> makeAllCells() {
        return Stream.of(
            Arguments.of(new EmptyCell(), false, false),
            Arguments.of(new LandMineCell(), true, false),
            Arguments.of(new NumberCell(0), false, true)
        );
    }

    public static Stream<Arguments> makeAllEmptyCellAndLandMineCells() {

        return Stream.of(
            Arguments.of(new EmptyCell() , CellSnapshotStatus.EMPTY),
            Arguments.of(new LandMineCell() , CellSnapshotStatus.LAND_MINE)
        );
    }

    @DisplayName("각각의 셀을 생성한다.")
    @ParameterizedTest
    @MethodSource("makeAllCells")
    void 각각의_셀을_생성한다(Cell cell, boolean isLandMine, boolean hasLandMineCount) {
        // given
        // when
        // then
        assertThat(cell).isNotNull();
        assertThat(cell.isLandMine()).isEqualTo(isLandMine);
        assertThat(cell.hasLandMineCount()).isEqualTo(hasLandMineCount);
    }
    
    @DisplayName("빈 셀과 폭탄셀을 열면 열림 상태로 변경된다.")
    @ParameterizedTest
    @MethodSource("makeAllEmptyCellAndLandMineCells")
    void 빈_셀과_폭탄셀을_열면_열림_상태로_변경된다(Cell cell, CellSnapshotStatus cellSnapshotStatus) {
        // given
        // when
        cell.open();
        // then
        assertThat(cell.isOpened()).isTrue();
        assertThat(cell.getSnapshot()).isEqualTo(CellSnapshot.of(cellSnapshotStatus,0));
    }

    @DisplayName("셀에 깃발을 꼽으면 깃발 상태로 변경된다.")
    @Test
    void 셀에_깃발을_꼽으면_깃발_상태로_변경된다() {
        // given
        Cell cell = new EmptyCell();
        // when
        cell.flag();
        // then
        assertThat(cell.getSnapshot()).isEqualTo(CellSnapshot.ofFlag());
    }

}

//BICEP
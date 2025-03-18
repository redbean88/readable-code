package cleancode.minesweeper.tobe.minesweeper.board.cell;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CellSnapshotTest {

    @DisplayName("숫자 셀 상태를 생성한다.")
    @Test
    void 숫자_셀_상태를_생성한다(){
        // given
        int targetnearbyLandMineCount = 4;
        CellSnapshot cellSnapshot = CellSnapshot.ofNumber(targetnearbyLandMineCount);
        // when
        int nearbyLandMineCount = cellSnapshot.getNearbyLandMineCount();
        CellSnapshotStatus status = cellSnapshot.getStatus();
        boolean sameStatus = cellSnapshot.isSameStatus(CellSnapshotStatus.NUMBER);

        // then
        assertThat(nearbyLandMineCount).isEqualTo(targetnearbyLandMineCount);
        assertThat(status).isEqualTo(CellSnapshotStatus.NUMBER);
        assertThat(sameStatus).isTrue();
    }

    @DisplayName("같은 숫자 셀을 비교한다.")
    @Test
    void 같은_숫자_셀을_비교한다(){
        // given
        int targetnearbyLandMineCount = 4;
        CellSnapshot cellSnapshot = CellSnapshot.ofNumber(targetnearbyLandMineCount);
        CellSnapshot cellSnapshot2 = CellSnapshot.ofNumber(targetnearbyLandMineCount);
        // when
        int nearbyLandMineCount = cellSnapshot.getNearbyLandMineCount();
        CellSnapshotStatus status = cellSnapshot.getStatus();
        int nearbyLandMineCount2 = cellSnapshot2.getNearbyLandMineCount();
        CellSnapshotStatus status2 = cellSnapshot2.getStatus();

        // then
        assertThat(nearbyLandMineCount).isEqualTo(nearbyLandMineCount2);
        assertThat(status).isEqualTo(status2);
        assertThat(cellSnapshot).hasSameHashCodeAs(cellSnapshot2);
    }

}
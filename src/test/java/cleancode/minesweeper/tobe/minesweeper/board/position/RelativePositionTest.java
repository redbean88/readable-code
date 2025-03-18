package cleancode.minesweeper.tobe.minesweeper.board.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RelativePositionTest {

    @DisplayName("상대위치를 생성한다.")
    @Test
    void 상대위치를_생성한다() {
        // given
        int deltaRow = 2;
        int deltaCol = 2;
        // when
        RelativePosition relativePosition = RelativePosition.of(deltaRow, deltaCol);
        // then
        assertThat(relativePosition).isNotNull();
        assertThat(relativePosition.getDeltaRow()).isEqualTo(deltaRow);
        assertThat(relativePosition.getDeltaCol()).isEqualTo(deltaCol);
    }

    @DisplayName("상대위치 간에 일치여부를 확인한다.")
    @Test
    void 상대위치_간에_일치여부를_확인한다() {
        // given
        int deltaRow = 2;
        int deltaCol = 2;
        // when
        RelativePosition relativePosition = RelativePosition.of(deltaRow, deltaCol);
        RelativePosition relativePosition2 = RelativePosition.of(deltaRow, deltaCol);
        // then
        assertThat(relativePosition2).isNotNull();
        assertThat(relativePosition).isNotNull()
            .isEqualTo(relativePosition2)
            .hasSameHashCodeAs(relativePosition2.hashCode());
    }

}
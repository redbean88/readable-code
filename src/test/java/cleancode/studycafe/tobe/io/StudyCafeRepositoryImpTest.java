package cleancode.studycafe.tobe.io;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import cleancode.studycafe.tobe.exception.FileReadException;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.repository.StudyCafeRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudyCafeRepositoryImpTest {

    @DisplayName("csv에서 이용권 목록 불러오기")
    @Test
    void csv에서_이용권_목록_불러오기() throws FileReadException {
        // given
        String passListCsv = "src/main/resources/cleancode/studycafe/pass-list.csv";
        String lockerCsv = "src/main/resources/cleancode/studycafe/locker.csv";
        StudyCafeRepository studyCafeRepository = new StudyCafeRepositoryImp(passListCsv,
            lockerCsv);
        // when
        List<StudyCafePass> studyCafePasses = studyCafeRepository.findAllStudyCafePasses().getStudyCafePassList();

        // then
        assertThat(studyCafePasses).hasSizeGreaterThan(0).hasSize(13);
    }

    @DisplayName("csv에서 이용권 목록 불러오기 실패")
    @Test
    void csv에서_이용권_목록_불러오기_실패() {
        // given
        String passListCsv = "testPath";
        String lockerCsv = "src/main/resources/cleancode/studycafe/locker.csv";
        StudyCafeRepository studyCafeRepository = new StudyCafeRepositoryImp(passListCsv,
            lockerCsv);
        // when
        // then
        assertThatThrownBy(studyCafeRepository::findAllStudyCafePasses)
            .isInstanceOf(FileReadException.class)
            .hasMessageContaining("파일을 읽는데 실패했습니다.");
    }

    @DisplayName("csv에서 사물함 목록 불러오기")
    @Test
    void csv에서_사물함_목록_불러오기() throws FileReadException {
        // given
        String passListCsv = "testPath";
        String lockerCsv = "src/main/resources/cleancode/studycafe/locker.csv";
        StudyCafeRepository studyCafeRepository = new StudyCafeRepositoryImp(passListCsv,
            lockerCsv);
        // when
        // then
        List<StudyCafeLockerPass> studyCafeLockerPasses = studyCafeRepository.findAllLockerPasses().getStudyCafeLockerPassList();
        assertThat(studyCafeLockerPasses).hasSizeGreaterThan(0).hasSize(2);
    }

    @DisplayName("csv에서 사물함 목록 불러오기 실패")
    @Test
    void csv에서_사물함_목록_불러오기_실패() {
        // given
        String passListCsv = "testPath";
        String lockerCsv = "testPath";
        StudyCafeRepository studyCafeRepository = new StudyCafeRepositoryImp(passListCsv,
            lockerCsv);
        // when
        // then
        assertThatThrownBy(studyCafeRepository::findAllLockerPasses)
            .isInstanceOf(FileReadException.class)
            .hasMessage("파일을 읽는데 실패했습니다.");
    }

}
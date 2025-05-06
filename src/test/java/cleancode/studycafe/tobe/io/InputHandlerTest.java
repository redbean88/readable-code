package cleancode.studycafe.tobe.io;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.exception.FileReadException;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.repository.StudyCafeRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InputHandlerTest {


    private InputHandler inputHandler = new InputHandler();
    private StudyCafeRepository studyCafeRepository;

    @BeforeEach
    void setUp() {
        String lockerCsv = "src/main/resources/cleancode/studycafe/locker.csv";
        String passListCsv = "src/main/resources/cleancode/studycafe/pass-list.csv";
        studyCafeRepository = new StudyCafeRepositoryImp(passListCsv, lockerCsv);
    }

    @DisplayName("시간권을 선택한다.")
    @Test
    void 시간권을_선택한다() {
        // given
        String inputText = "1";
        // when

        StudyCafePassType passTypeSelectingUserAction = inputHandler.getPassTypeBy(inputText);
        // then
        assertThat(passTypeSelectingUserAction).isEqualTo(StudyCafePassType.HOURLY);
    }


    @DisplayName("주간권을 선택한다.")
    @Test
    void 주간권을_선택한다() {
        // given
        String inputText = "2";
        // when
        StudyCafePassType passTypeSelectingUserAction = inputHandler.getPassTypeBy(inputText);
        // then
        assertThat(passTypeSelectingUserAction).isEqualTo(StudyCafePassType.WEEKLY);
    }

    @DisplayName("고정석을 선택한다.")
    @Test
    void 고정석을_선택한다() {
        // given
        String inputText = "3";
        // when
        StudyCafePassType passTypeSelectingUserAction = inputHandler.getPassTypeBy(inputText);
        // then
        assertThat(passTypeSelectingUserAction).isEqualTo(StudyCafePassType.FIXED);
    }

    @DisplayName("1,2,3이외의 값을 선택한다.")
    @Test
    void 미지원값을_선택한다() {
        // given
        String inputText = "4";
        // when
        // then
        assertThatThrownBy(() -> inputHandler.getPassTypeBy(inputText))
            .isInstanceOf(AppException.class)
            .hasMessage("잘못된 입력입니다.");
    }

    @DisplayName("시간권 목록 중에 선택한다.")
    @ParameterizedTest
    @CsvSource(value = {
        "1,HOURLY,2,4000,0.0",
        "2,HOURLY,4,6500,0.0",
        "3,HOURLY,6,9000,0.0",
        "4,HOURLY,8,11000,0.0",
        "5,HOURLY,10,12000,0.0",
        "6,HOURLY,12,13000,0.0"
    })
    void 시간권_목록_중에_선택한다(String inputText, StudyCafePassType passType, int duration, int price, double discountRate) throws FileReadException {
        // given
        List<StudyCafePass> studyCafePasses = studyCafeRepository.findAllStudyCafePasses();

        List<StudyCafePass> passes = studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.getPassType() == passType)
            .toList();

        // when
        StudyCafePass selectPass = inputHandler.getSelectPass(passes, inputText);

        // then
        assertThat(selectPass).extracting("passType", "duration", "price", "discountRate")
            .containsExactly(passType, duration, price, discountRate);
    }

    @DisplayName("지원하지 않는 이용권 목록 중에 선택한다.")
    @ParameterizedTest
    @CsvSource(value = {"HOURLY","WEEKLY","FIXED"})
    void 지원하지_않는_이용권_목록_중에_선택한다(StudyCafePassType studyCafePassType) throws FileReadException {
        // given
        List<StudyCafePass> studyCafePasses = studyCafeRepository.findAllStudyCafePasses();

        List<StudyCafePass> passes = studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.getPassType() == studyCafePassType)
            .toList();
        String inputText = String.valueOf(passes.size()+1);

        // when
        // then
        assertThatThrownBy(() -> inputHandler.getSelectPass(passes, inputText))
            .isInstanceOf(AppException.class)
            .hasMessage("잘못된 입력입니다.");
    }


    @DisplayName("주간권 목록 중에 선택한다.")
    @ParameterizedTest
    @CsvSource(value = {
        "1,WEEKLY,1,60000,0.0",
        "2,WEEKLY,2,100000,0.1",
        "3,WEEKLY,3,130000,0.1",
        "4,WEEKLY,4,150000,0.1",
        "5,WEEKLY,12,400000,0.15"
    })
    void 주간권_목록_중에_선택한다(String inputText, StudyCafePassType passType, int duration, int price, double discountRate) throws FileReadException {
        // given
        List<StudyCafePass> studyCafePasses = studyCafeRepository.findAllStudyCafePasses();

        List<StudyCafePass> passes = studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.getPassType() == passType)
            .toList();

        // when
        StudyCafePass selectPass = inputHandler.getSelectPass(passes, inputText);

        // then
        assertThat(selectPass).extracting("passType", "duration", "price", "discountRate")
            .containsExactly(passType, duration, price, discountRate);
    }

    @DisplayName("고정석 목록 중에 선택한다.")
    @ParameterizedTest
    @CsvSource(value = {
        "1,FIXED,4,250000,0.1",
        "2,FIXED,12,700000,0.15"
    })
    void 고정석_목록_중에_선택한다(String inputText, StudyCafePassType passType, int duration, int price, double discountRate) throws FileReadException {
        // given
        List<StudyCafePass> studyCafePasses = studyCafeRepository.findAllStudyCafePasses();

        List<StudyCafePass> passes = studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.getPassType() == passType)
            .toList();

        // when
        StudyCafePass selectPass = inputHandler.getSelectPass(passes, inputText);

        // then
        assertThat(selectPass).extracting("passType", "duration", "price", "discountRate")
            .containsExactly(passType, duration, price, discountRate);
    }
}
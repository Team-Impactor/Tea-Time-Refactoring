package Server.TeaTimeProjectRefactoring.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * @author 신건우
 * @desc Response로 반환할 Error Response
 */
@Getter @Builder
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;

    // 일반적인 Exception을 처리하는 팩토리 메서드
    public static ErrorResponse of(String errorCode, String errorMessage) {
        return ErrorResponse.builder()
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
    }

    // BindindException을 처리하는 팩토리 메서드
    public static ErrorResponse of(String errorCode, BindingResult bindingResult) {
        return ErrorResponse.builder()
                .errorCode(errorCode)
                .errorMessage(createErrorMessage(bindingResult))
                .build();
    }

    /* 메시지 생성 내부 함수 */
    private static String createErrorMessage(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;

        // BindingResult의 FieldError를 리스트에 담음
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        // isFirst = 리스트의 첫번째를 제외하고 나머지 문자열 사이에 ","를 추가하여 분리함
        for(FieldError fieldError : fieldErrors) {
            if (!isFirst) sb.append(", "); else isFirst = false;

            sb.append("[");
            sb.append(fieldError.getField());
            sb.append("]");
            sb.append(fieldError.getDefaultMessage());
        }

        return sb.toString();
    }
}

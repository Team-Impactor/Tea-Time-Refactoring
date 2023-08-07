package Server.TeaTimeProjectRefactoring.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorCode {
    // 반환할 httpStatus 값, 에러 코드, 에러 메세지를 관리하는 Enum 클래스
    TEST(HttpStatus.INTERNAL_SERVER_ERROR , "001","business exception test")
    ;

    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

    private HttpStatus httpStatus;
    private String errorCode;
    private String message;
}
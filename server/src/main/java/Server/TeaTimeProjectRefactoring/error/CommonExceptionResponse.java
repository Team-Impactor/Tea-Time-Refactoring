package Server.TeaTimeProjectRefactoring.error;

import lombok.Getter;

/**
 * @author 신건우
 * @desc Custom Common Exception Response
 */
public class CommonExceptionResponse {

    @Getter private int code;
    @Getter private String message;

    public CommonExceptionResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

package Server.TeaTimeProjectRefactoring.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author 신건우
 * @desc Custom Common Exception
 */
public class CommonException extends RuntimeException {

    @Getter private HttpStatus status;

    public CommonException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}

package Server.TeaTimeProjectRefactoring.error;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @author 신건우
 * @desc Global Error Handler
 */
@RestControllerAdvice
public class GlobalErrorHandler {

    private static final Logger log = LogManager.getLogger(GlobalErrorHandler.class);

    /**
     * @desc Custom CommonException
     * @desc 어떤 Exception이 날 지 모를때 사용
     */
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<CommonExceptionResponse> handleCommonException(CommonException e) {
        CommonExceptionResponse error = new CommonExceptionResponse(
                e.getStatus().value(),
                e.getMessage() != null ? e.getMessage() : "에러 메시지 가져오기 실패");

        return ResponseEntity.status(e.getStatus()).body(error);
    }

    /**
     * @desc 컨트롤러의 입력값이 DTO고 입력값 검증 실패 시 BindException 발생
     * @desc 이 BindException을 전역적으로 처리하고 컨트롤러에 Error Response 반환
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        log.error("Bind Exception", e);
        ErrorResponse error = ErrorResponse.of(
                HttpStatus.BAD_REQUEST.toString(),
                e.getBindingResult());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * @desc @RequestParam 값 바인딩에 실패했을 경우 발생하는 Exception
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("Method Argument Type Mismatch Exception", e);
        ErrorResponse error = ErrorResponse.of(
                HttpStatus.BAD_REQUEST.toString(),
                e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * @desc 지원하지 않는 HTTP METHOD를 호출했을 경우 발생되는 Exception
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("Http Request Method Not Support Exception", e);
        ErrorResponse error = ErrorResponse.of(
                HttpStatus.METHOD_NOT_ALLOWED.toString(),
                e.getMessage());

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    /**
     * @desc 나머지 예외 처리
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Exception", e);
        ErrorResponse error = ErrorResponse.of(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

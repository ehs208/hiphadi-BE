package hiphadi.menu.api;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> handleGenericException(Exception ex) {
        log.error(ex.getMessage());
        return ApiResponse.of(
            HttpStatus.INTERNAL_SERVER_ERROR,
            ex.getMessage()
        );
    }

    @ExceptionHandler(IllegalAccessException.class)
    protected ApiResponse<Object> illegalArgumentException(IllegalArgumentException e) {
        return ApiResponse.of(
            HttpStatus.BAD_REQUEST,
            e.getMessage()
        );
    }
}

package hiphadi.menu.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
public class ApiResponse<T> {

    private int code;
    private HttpStatus status;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ApiResponse(HttpStatus status, String message, T data) {
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> of(HttpStatus status, String message, T data) {
        return new ApiResponse<>(status, message, data);
    }

    public static <T> ApiResponse<T> of(HttpStatus status, String message) {
        return new ApiResponse<>(status, message, null);
    }

    public static <T> ApiResponse<T> of(HttpStatus status, T data) {
        return of(status, status.name(), data);
    }

    public static <T> ApiResponse<T> ok(T data) {
        return of(HttpStatus.OK, data);
    }

    public static <T> ApiResponse<T> created(T data) {
        return of(HttpStatus.CREATED, data);
    }

}

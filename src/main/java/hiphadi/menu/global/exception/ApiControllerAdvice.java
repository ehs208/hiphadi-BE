package hiphadi.menu.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import hiphadi.menu.global.response.GlobalResponseDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<GlobalResponseDto<Void>> handleCustomException(CustomException e) {
		log.warn("커스텀 예외 발생: {} - {}", e.getErrorCode().name(), e.getMessage());
		return ResponseEntity
			.status(e.getErrorCode().getHttpStatus())
			.body(GlobalResponseDto.fail(e.getErrorCode().getHttpStatus(), e.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<GlobalResponseDto<Void>> handleValidationException(MethodArgumentNotValidException e) {
		String message = e.getBindingResult().getFieldErrors().stream()
			.map(FieldError::getDefaultMessage)
			.findFirst()
			.orElse(ErrorCode.INVALID_INPUT.getMessage());
		log.warn("입력값 검증 실패: {}", message);
		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(GlobalResponseDto.fail(HttpStatus.BAD_REQUEST, message));
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<GlobalResponseDto<Void>> handleMaxUploadSize(MaxUploadSizeExceededException e) {
		log.warn("파일 크기 초과: {}", e.getMessage());
		return ResponseEntity
			.status(ErrorCode.FILE_TOO_LARGE.getHttpStatus())
			.body(GlobalResponseDto.fail(ErrorCode.FILE_TOO_LARGE.getHttpStatus(), ErrorCode.FILE_TOO_LARGE.getMessage()));
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<GlobalResponseDto<Void>> handleNoResourceFound(NoResourceFoundException e) {
		// SPA 라우팅 요청 (예: /admin/login)이 백엔드로 오는 경우 - 로그 생략
		log.debug("존재하지 않는 리소스 요청: {}", e.getResourcePath());
		return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(GlobalResponseDto.fail(HttpStatus.NOT_FOUND, "요청한 리소스를 찾을 수 없습니다."));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<GlobalResponseDto<Void>> handleException(Exception e) {
		log.error("예상하지 못한 오류 발생", e);
		return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(GlobalResponseDto.fail(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR.getMessage()));
	}
}

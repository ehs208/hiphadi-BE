package hiphadi.menu.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	// Common
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 문제가 발생했습니다. 잠시 후 다시 시도해주세요."),
	INVALID_INPUT(HttpStatus.BAD_REQUEST, "입력값이 올바르지 않습니다."),

	// Product
	PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 메뉴를 찾을 수 없습니다."),

	// Category
	CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 카테고리를 찾을 수 없습니다."),

	// Suggestion
	SUGGESTION_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 건의사항을 찾을 수 없습니다."),

	// Image
	FILE_TOO_LARGE(HttpStatus.BAD_REQUEST, "파일 크기가 10MB를 초과합니다."),
	IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 이미지를 찾을 수 없습니다."),
	IMAGE_IN_USE(HttpStatus.CONFLICT, "사용 중인 이미지는 삭제할 수 없습니다."),
	IMAGE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다. 잠시 후 다시 시도해주세요."),
	FILE_EMPTY(HttpStatus.BAD_REQUEST, "파일이 비어있습니다."),
	INVALID_FILE_EXTENSION(HttpStatus.BAD_REQUEST, "허용되지 않는 파일 형식입니다."),
	INVALID_FILE_TYPE(HttpStatus.BAD_REQUEST, "허용되지 않는 파일 타입입니다."),
	INVALID_FILENAME(HttpStatus.BAD_REQUEST, "파일명이 올바르지 않습니다."),
	NO_FILE_EXTENSION(HttpStatus.BAD_REQUEST, "파일 확장자가 없습니다.");

	private final HttpStatus httpStatus;
	private final String message;
}

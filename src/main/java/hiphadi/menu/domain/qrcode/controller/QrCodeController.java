package hiphadi.menu.domain.qrcode.controller;

import java.io.IOException;
import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;

import hiphadi.menu.domain.qrcode.service.QrCodeService;
import hiphadi.menu.domain.qrcode.dto.CreateQrCodeRequest;
import hiphadi.menu.global.config.ApiKeyConfig;
import hiphadi.menu.domain.qrcode.domain.QrCode;
import hiphadi.menu.global.response.GlobalResponseDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/qrcode")
@RequiredArgsConstructor
public class QrCodeController {
	private final QrCodeService qrCodeService;
	private final ApiKeyConfig apiKeyConfig;

	@PostMapping("")
	public GlobalResponseDto<String> createQrCode(@RequestBody CreateQrCodeRequest request,  @RequestHeader("X-API-KEY") String apiKey) throws
		IOException,
		WriterException {
		if (!apiKeyConfig.isValidApiKey(apiKey)) {
			throw new IllegalArgumentException("유효하지 않은 API 키입니다.");
		}

		String trackingId = qrCodeService.createQrCode(request);
		return GlobalResponseDto.success(trackingId);
	}

	@GetMapping("/redirect/{trackingId}")
	public ResponseEntity<Void> redirect(
		@PathVariable String trackingId) {
		QrCode qrCode = qrCodeService.getQrCode(trackingId);
		return ResponseEntity.status(HttpStatus.FOUND)
			.location(URI.create(qrCode.getTargetUrl()))
			.build();
	}

}

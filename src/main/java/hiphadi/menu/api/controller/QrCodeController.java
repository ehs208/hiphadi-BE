package hiphadi.menu.api.controller;

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

import hiphadi.menu.api.ApiResponse;
import hiphadi.menu.api.service.QrCodeService;
import hiphadi.menu.api.service.request.CreateQrCodeRequest;
import hiphadi.menu.config.ApiKeyConfig;
import hiphadi.menu.domain.qrcode.QrCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/qrcode")
@RequiredArgsConstructor
public class QrCodeController {
	private final QrCodeService qrCodeService;
	private final ApiKeyConfig apiKeyConfig;

	@PostMapping("")
	public ApiResponse<String> createQrCode(@RequestBody CreateQrCodeRequest request,  @RequestHeader("X-API-KEY") String apiKey) throws
		IOException,
		WriterException {
		if (!apiKeyConfig.isValidApiKey(apiKey)) {
			throw new RuntimeException("Invalid API Key");
		}

		String trackingId = qrCodeService.createQrCode(request);
		return ApiResponse.ok(trackingId);
	}

	@GetMapping("/redirect/{trackingId}")
	public ResponseEntity<Void> redirect(
		@PathVariable String trackingId,
		HttpServletRequest request) {
		qrCodeService.recordVisit(
			trackingId,
			request.getRemoteAddr(),
			request.getHeader("User-Agent")
		);
		QrCode qrCode = qrCodeService.getQrCode(trackingId);
		return ResponseEntity.status(HttpStatus.FOUND)
			.location(URI.create(qrCode.getTargetUrl()))
			.build();
	}

	// @GetMapping("/stats/{trackingId}")
	// public ResponseEntity<QrCodeStats> getStats(@PathVariable String trackingId) {
	// 	QrCodeStats stats = qrCodeService.getStats(trackingId);
	// 	return ResponseEntity.ok(stats);
	// }
}

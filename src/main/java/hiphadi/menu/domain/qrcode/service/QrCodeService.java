package hiphadi.menu.domain.qrcode.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import hiphadi.menu.domain.qrcode.dto.CreateQrCodeRequest;
import hiphadi.menu.domain.qrcode.domain.QrCode;
import hiphadi.menu.domain.qrcode.domain.repository.QrCodeRepository;
import hiphadi.menu.domain.visitLog.VisitLog;
import hiphadi.menu.domain.visitLog.VisitLogRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QrCodeService {
	private final QrCodeRepository qrCodeRepository;
	private final VisitLogRepository visitLogRepository;

	public String createQrCode(CreateQrCodeRequest request) throws IOException, WriterException {
			// 트래킹 ID 생성
			String trackingId = UUID.randomUUID().toString();

			// QR 코드 엔티티 생성
			QrCode qrcode = QrCode.createQrCode(trackingId, request.getTargetUrl());
			qrCodeRepository.save(qrcode);

			// QR 코드 이미지 생성
			String redirectUrl = "https://api.hiphadi.store/api/qrcode/redirect/" + trackingId;
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(redirectUrl, BarcodeFormat.QR_CODE, 500, 500);

			// 이미지 저장 경로 설정
			Path directoryPath = Paths.get("qrcodes");
			Path filePath = directoryPath.resolve(trackingId + ".png");

			// 디렉토리 생성
			if (!Files.exists(directoryPath)) {
				Files.createDirectories(directoryPath);
			}

			// 이미지 저장
			MatrixToImageWriter.writeToPath(bitMatrix, "PNG", filePath);

			return trackingId;
	}

	public void recordVisit(String trackingId, String ipAddress, String userAgent) {
		QrCode qrCode = qrCodeRepository.findByTrackingId(trackingId);
		VisitLog visitLog = VisitLog.createVisitLog(qrCode, ipAddress, userAgent);

		qrCode.addVisits();
		visitLogRepository.save(visitLog);
		qrCodeRepository.save(qrCode);
	}

	// public QrCodeStats getStats(String trackingId) {
	// 	QrCode qrCode = qrCodeRepository.findByTrackingId(trackingId);
	//
	// 	return QrCodeStats.builder()
	// 		.totalVisits(qrCode.getVisits())
	// 		.createdAt(qrCode.getCreatedAt())
	// 		.targetUrl(qrCode.getTargetUrl())
	// 		.hourlyStats(calculateHourlyStats(qrCode.getVisitLogs()))
	// 		.build();
	// }

	public QrCode getQrCode(String trackingId) {
		return qrCodeRepository.findByTrackingId(trackingId);
	}
}


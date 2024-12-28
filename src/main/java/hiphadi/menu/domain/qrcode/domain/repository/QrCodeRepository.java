package hiphadi.menu.domain.qrcode.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hiphadi.menu.domain.qrcode.domain.QrCode;

@Repository
public interface QrCodeRepository extends JpaRepository<QrCode, Long> {
	QrCode findByTrackingId(String trackingId);
}

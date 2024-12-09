package hiphadi.menu.domain.qrcode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrCodeRepository extends JpaRepository<QrCode, Long> {
	QrCode findByTrackingId(String trackingId);
}

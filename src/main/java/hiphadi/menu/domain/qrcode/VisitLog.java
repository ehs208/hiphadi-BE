package hiphadi.menu.domain.qrcode;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class VisitLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private QrCode qrCode;

	private LocalDateTime visitedAt;
	private String ipAddress;
	private String userAgent;

	@Builder
	private VisitLog(QrCode qrCode, LocalDateTime visitedAt, String ipAddress, String userAgent) {
		this.qrCode = qrCode;
		this.visitedAt = visitedAt;
		this.ipAddress = ipAddress;
		this.userAgent = userAgent;
	}

	public static VisitLog createVisitLog(QrCode qrCode, String ipAddress, String userAgent) {
		return VisitLog.builder()
			.qrCode(qrCode)
			.visitedAt(LocalDateTime.now())
			.ipAddress(ipAddress)
			.userAgent(userAgent)
			.build();
	}
}

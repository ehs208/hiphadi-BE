package hiphadi.menu.domain.qrcode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class QrCode {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String trackingId;
	private String targetUrl;
	private LocalDateTime createdAt;
	private int visits;

	@OneToMany(mappedBy = "qrCode", cascade = CascadeType.ALL)
	private List<VisitLog> visitLogs = new ArrayList<>();

	@Builder
	private QrCode(String trackingId, String targetUrl, LocalDateTime createdAt, int visits) {
		this.trackingId = trackingId;
		this.targetUrl = targetUrl;
		this.createdAt = createdAt;
		this.visits = visits;
	}

	public static QrCode createQrCode(String trackingId, String targetUrl) {
		return QrCode.builder()
			.trackingId(trackingId)
			.targetUrl(targetUrl)
			.createdAt(LocalDateTime.now())
			.visits(0)
			.build();
	}

	public void addVisits() {
		this.visits++;
	}
}

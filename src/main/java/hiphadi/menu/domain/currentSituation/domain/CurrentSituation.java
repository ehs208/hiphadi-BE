package hiphadi.menu.domain.currentSituation.domain;

import hiphadi.menu.domain.menuAvailability.domain.Situation;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CurrentSituation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Situation situation;  // PARTY, NORMAL

	@NotNull
	private boolean isActive;    // true/false


	private void activate() {
		this.isActive = true;
	}

	private void deactivate() {
		this.isActive = false;
	}

	// 정적 팩토리 메서드
	public static CurrentSituation changeSituationStatus(CurrentSituation situationType, boolean active) {
		if (active) {
			situationType.activate();
		} else {
			situationType.deactivate();
		}
		return situationType;
	}

}


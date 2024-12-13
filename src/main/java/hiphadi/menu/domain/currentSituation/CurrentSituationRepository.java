package hiphadi.menu.domain.currentSituation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hiphadi.menu.domain.menuAvailability.Situation;

@Repository
public interface CurrentSituationRepository extends JpaRepository<CurrentSituation, Long> {
	CurrentSituation findCurrentSituationByIsActive(boolean isActive);
}

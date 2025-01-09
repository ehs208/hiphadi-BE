package hiphadi.menu.domain.currentSituation.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hiphadi.menu.domain.currentSituation.domain.CurrentSituation;
import hiphadi.menu.domain.menu.domain.Situation;

@Repository
public interface CurrentSituationRepository extends JpaRepository<CurrentSituation, Long> {
	CurrentSituation findCurrentSituationByIsActive(boolean isActive);
	CurrentSituation findBySituation(Situation situation);
}

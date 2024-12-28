package hiphadi.menu.domain.currentSituation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hiphadi.menu.domain.currentSituation.domain.CurrentSituation;
import hiphadi.menu.domain.currentSituation.domain.repository.CurrentSituationRepository;
import hiphadi.menu.domain.menuAvailability.domain.Situation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CurrentSituationService {

	private final CurrentSituationRepository currentSituationRepository;

	@Transactional
	public void changeSituationStatus(Situation situation) {

		CurrentSituation normalSituation = currentSituationRepository.findBySituation(Situation.NORMAL);
		CurrentSituation partySituation = currentSituationRepository.findBySituation(Situation.PARTY);

		if(situation == Situation.NORMAL) {
			normalSituation.changeSituationStatus(normalSituation, true);
			partySituation.changeSituationStatus(partySituation, false);

		} else if(situation == Situation.PARTY) {
			normalSituation.changeSituationStatus(normalSituation, false);
			partySituation.changeSituationStatus(partySituation, true);
		}

	}

	public Situation getCurrentSituation() {
		CurrentSituation situation = currentSituationRepository.findCurrentSituationByIsActive(true);
		return situation.getSituation();
	}

}

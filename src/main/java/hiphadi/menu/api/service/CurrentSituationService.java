package hiphadi.menu.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hiphadi.menu.domain.currentSituation.CurrentSituation;
import hiphadi.menu.domain.currentSituation.CurrentSituationRepository;
import hiphadi.menu.domain.menuAvailability.Situation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CurrentSituationService {

	private final CurrentSituationRepository CurrentSituationRepository;

	@Transactional
	public void changeSituationStatus(Situation situation) {

		CurrentSituation normalSituation = CurrentSituationRepository.findBySituation(Situation.NORMAL);
		CurrentSituation partySituation = CurrentSituationRepository.findBySituation(Situation.PARTY);

		if(situation == Situation.NORMAL) {
			normalSituation.changeSituationStatus(normalSituation, true);
			partySituation.changeSituationStatus(partySituation, false);

		} else if(situation == Situation.PARTY) {
			normalSituation.changeSituationStatus(normalSituation, false);
			partySituation.changeSituationStatus(partySituation, true);
		}

	}

}

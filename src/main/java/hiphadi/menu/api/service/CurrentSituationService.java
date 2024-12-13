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


}

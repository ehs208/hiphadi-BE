package hiphadi.menu.domain.currentSituation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import hiphadi.menu.domain.currentSituation.service.CurrentSituationService;
import hiphadi.menu.domain.menuAvailability.domain.Situation;
import hiphadi.menu.global.response.GlobalResponseDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/situation")
@RestController
public class CurrentSituationController {

	private final CurrentSituationService currentSituationService;

	@PatchMapping("{situation}")
	public GlobalResponseDto<Void> changeSituation(@PathVariable Situation situation) {
		currentSituationService.changeSituationStatus(situation);
		return GlobalResponseDto.success();
	}

	@GetMapping("")
	public GlobalResponseDto<Situation> getCurrentSituation() {
		return GlobalResponseDto.success(currentSituationService.getCurrentSituation());
	}

}

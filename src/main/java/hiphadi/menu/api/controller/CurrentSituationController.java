package hiphadi.menu.api.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hiphadi.menu.api.ApiResponse;
import hiphadi.menu.api.service.CurrentSituationService;
import hiphadi.menu.domain.menuAvailability.Situation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/situation")
@RestController
public class CurrentSituationController {

	private final CurrentSituationService currentSituationService;

	@PatchMapping("{situation}")
	public ApiResponse<Void> changeSituation(@PathVariable Situation situation) {
		currentSituationService.changeSituationStatus(situation);
		return ApiResponse.ok(null);
	}

}

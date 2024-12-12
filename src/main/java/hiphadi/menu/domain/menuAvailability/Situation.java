package hiphadi.menu.domain.menuAvailability;

public enum Situation {
	PARTY("파티"),
	NORMAL("일반"),
	;

	private final String text;

	Situation(String text) {
		this.text = text;
	}
}

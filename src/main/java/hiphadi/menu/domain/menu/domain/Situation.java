package hiphadi.menu.domain.menu.domain;

public enum Situation {
	PARTY("파티"),
	NORMAL("일반"),
	;

	private final String text;

	Situation(String text) {
		this.text = text;
	}
}

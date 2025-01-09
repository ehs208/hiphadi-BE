package hiphadi.menu.domain.product.domain;

public enum RecommendStatus {

	RECOMMEND("추천 상품"),
	NORMAL("일반 상품"),
	;

	private final String text;

	RecommendStatus(String text) {
		this.text = text;
	}

}

package hiphadi.menu.domain.statistics.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PopularMenusResponse {
	private List<MenuClickInfo> menus;
	private long totalClicks;
	private int totalMenuCount;

	@Builder
	public PopularMenusResponse(List<MenuClickInfo> menus, long totalClicks, int totalMenuCount) {
		this.menus = menus;
		this.totalClicks = totalClicks;
		this.totalMenuCount = totalMenuCount;
	}

	@Getter
	public static class MenuClickInfo {
		private Long productId;
		private String name;
		private int clicks;

		@Builder
		public MenuClickInfo(Long productId, String name, int clicks) {
			this.productId = productId;
			this.name = name;
			this.clicks = clicks;
		}
	}
}

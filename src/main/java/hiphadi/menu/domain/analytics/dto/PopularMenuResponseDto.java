package hiphadi.menu.domain.analytics.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PopularMenuResponseDto {
	private List<MenuInfo> menus;
	private int totalViews;
	private int totalMenuCount;

	@Builder
	public PopularMenuResponseDto(List<MenuInfo> menus, int totalViews, int totalMenuCount) {
		this.menus = menus;
		this.totalViews = totalViews;
		this.totalMenuCount = totalMenuCount;
	}

	@Getter
	@NoArgsConstructor
	public static class MenuInfo {
		private String name;
		private int views;

		@Builder
		public MenuInfo(String name, int views) {
			this.name = name;
			this.views = views;
		}
	}
}

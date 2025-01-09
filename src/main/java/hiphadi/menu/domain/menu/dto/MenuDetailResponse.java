package hiphadi.menu.domain.menu.dto;

import java.util.List;
import java.util.stream.Collectors;

import hiphadi.menu.domain.menu.domain.Menu;
import hiphadi.menu.domain.product.domain.ProductImg;
import lombok.Getter;

@Getter
public class MenuDetailResponse {
	private Long id;
	private String name;
	private String description;
	private Long price;
	private List<String> imgPath;

	public MenuDetailResponse(Menu menu) {
		this.id = menu.getId();
		this.name = menu.getProduct().getName();
		this.description = menu.getProduct().getDescription();
		this.price = menu.getPrice();
		this.imgPath = menu.getProduct().getProductImage().stream()
			.map(ProductImg::getUrl)
			.collect(Collectors.toList());
	}
}



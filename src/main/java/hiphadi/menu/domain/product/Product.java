package hiphadi.menu.domain.product;

import hiphadi.menu.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String category;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Builder
    private Product(Long id, String name, String description, BigDecimal price, String category, ProductStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.status = status;
    }

    public static Product create(String name, String description, BigDecimal price, String category, ProductStatus status) {
        return Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .category(category)
                .status(status)
                .build();
    }

    public void update(String name, String description, BigDecimal price, String category, ProductStatus status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.status = status;
    }
}

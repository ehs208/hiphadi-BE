package hiphadi.menu.domain.product.domain;

public enum ProductStatus {

    SALE("판매 중"),
    SOLD_OUT("품절"),
    ;

    private final String text;

    ProductStatus(String text) {
        this.text = text;
    }

}

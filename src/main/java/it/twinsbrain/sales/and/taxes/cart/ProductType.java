package it.twinsbrain.sales.and.taxes.cart;

import java.util.List;

public enum ProductType {

    FOOD(List.of("chocolate", "bread")),
    BOOK(List.of("book")),
    MUSIC(List.of("cd", "mp3")),
    MEDICAL(List.of("pills")),
    OTHERS(List.of());

    private final List<String> keywords;

    ProductType(final List<String> keywords) {
        this.keywords = keywords;
    }

    public boolean match(final String productDescription) {
        return keywords.stream().anyMatch((keyword) ->
                (productDescription.toLowerCase().contains(keyword.toLowerCase())));
    }

}

package it.twinsbrain.sales.and.taxes.cart;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public enum ProductType {

    FOOD(asList("chocolate", "bread")),
    BOOK(asList("book")),
    MUSIC(asList("cd", "mp3")),
    MEDICAL(asList("pills")),
    OTHERS(Collections.<String>emptyList());

    ProductType(final List<String> keywords) {
        this.keywords = keywords;
    }

    private final List<String> keywords;

    public boolean match(final String productDescription) {
        return keywords.stream().anyMatch((keyword) ->
                (productDescription.toLowerCase().contains(keyword.toLowerCase())));
    }

}

package it.twinsbrain.sales.and.taxes.cart;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public enum ProductType {

    FOOD(asList("chocolate", "bread")),
    BOOK(singletonList("book")),
    MUSIC(asList("cd", "mp3")),
    MEDICAL(singletonList("pills")),
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

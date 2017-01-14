package it.twinsbrain.sales.and.taxes.cart;

import it.twinsbrain.sales.and.taxes.strategies.TaxStrategyFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class ShoppingCart {

    private final List<CartItem> items;
    private final TaxStrategyFactory taxStrategyFactory;
    
    public ShoppingCart(final TaxStrategyFactory taxStrategyFactory, final List<CartItem> items) {
        this.taxStrategyFactory = taxStrategyFactory;
        this.items = items;
    }

    public Receipt toReceipt() {
        return new Receipt(items.stream().map(itemWithTaxes()).collect(toList()));
    }

    private Function<CartItem, CartItem> itemWithTaxes() {
        return i -> i.accept(taxStrategyFactory.taxFor(i));
    }

    protected List<CartItem> items() {
        return unmodifiableList(items);
    }
}

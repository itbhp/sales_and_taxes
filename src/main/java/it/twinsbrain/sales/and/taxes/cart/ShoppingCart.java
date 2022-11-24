package it.twinsbrain.sales.and.taxes.cart;

import it.twinsbrain.sales.and.taxes.strategies.TaxStrategyFactory;

import java.util.List;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class ShoppingCart {

    private final List<CartItem> items;
    private final TaxStrategyFactory taxStrategyFactory;

    public ShoppingCart(
            final TaxStrategyFactory taxStrategyFactory,
            final List<CartItem> items
    ) {
        this.taxStrategyFactory = taxStrategyFactory;
        this.items = items;
    }

    public Receipt toReceipt() {
        return new Receipt(items.stream().map(this::itemWithTaxes).collect(toList()));
    }

    private CartItem itemWithTaxes(CartItem cartItem) {
        return cartItem.accept(taxStrategyFactory.taxFor(cartItem));
    }

    protected List<CartItem> items() {
        return unmodifiableList(items);
    }
}

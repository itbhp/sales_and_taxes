package it.twinsbrain.sales.and.taxes.cart;

import it.twinsbrain.sales.and.taxes.strategies.TaxStrategyFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {

    private final List<CartItem> items;
    private final TaxStrategyFactory taxStrategyFactory;

    public ShoppingCart(final TaxStrategyFactory taxStrategyFactory) {
        this.taxStrategyFactory = taxStrategyFactory;
        this.items = new LinkedList<>();
    }

    protected List<CartItem> listInputItems() {
        return Collections.unmodifiableList(items);
    }

    public Receipt toReceipt() {
        final List<CartItem> itemsWithTaxes = new LinkedList<>();
        for (CartItem item : items) {
            final CartItem itemWithTax = item.accept(taxStrategyFactory.createFrom(item));
            itemsWithTaxes.add(itemWithTax);
        }
        return new Receipt(itemsWithTaxes);
    }

    public void add(CartItem item) {
        items.add(item);
    }
}

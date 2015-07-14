package it.twinsbrain.sales.and.taxes.cart;

import it.twinsbrain.sales.and.taxes.strategies.TaxStrategyFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author paolo
 */
public class ShoppingCart {

    private List<CartItem> items;
    private TaxStrategyFactory taxStrategyFactory;

    public ShoppingCart(TaxStrategyFactory taxStrategyFactory) {
        this.taxStrategyFactory = taxStrategyFactory;
        this.items = new LinkedList<>();
    }

    protected List<CartItem> listInputItems() {
        return Collections.unmodifiableList(items);
    }

    public Receipt toReceipt() {
        List<CartItem> itemsWithTaxes = new LinkedList<>();
        for (CartItem item : items) {
            CartItem itemWithTax = item.accept(taxStrategyFactory.createFrom(item));
            itemsWithTaxes.add(itemWithTax);
        }
        return new Receipt(itemsWithTaxes);
    }

    public void add(CartItem item) {
        items.add(item);
    }
}

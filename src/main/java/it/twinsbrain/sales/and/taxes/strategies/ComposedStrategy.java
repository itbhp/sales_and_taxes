package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;

/**
 * @author paolo
 */
public class ComposedStrategy implements TaxStrategy {
    private final TaxStrategy current;
    private final TaxStrategy next;

    public ComposedStrategy(TaxStrategy current, TaxStrategy next){
        this.current = current;
        this.next = next;
    }


    @Override
    public CartItem updateItemWithTaxes(CartItem visitee) {
        return next.updateItemWithTaxes(current.updateItemWithTaxes(visitee));
    }
}

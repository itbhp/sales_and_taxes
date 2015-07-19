package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;

public class ComposedStrategy implements TaxStrategy {
    public final TaxStrategy current;
    public final TaxStrategy next;

    public ComposedStrategy(final TaxStrategy current, final TaxStrategy next) {
        this.current = current;
        this.next = next;
    }


    @Override
    public CartItem updateTaxesOn(final CartItem visitee) {
        return next.updateTaxesOn(current.updateTaxesOn(visitee));
    }
}

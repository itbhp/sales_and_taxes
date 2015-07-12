package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;

/**
 * @author paolo
 */
public class ComposedStrategy implements TaxStrategy {
    public final TaxStrategy current;
    public final TaxStrategy next;

    public ComposedStrategy(TaxStrategy current, TaxStrategy next){
        this.current = current;
        this.next = next;
    }


    @Override
    public CartItem updateTaxesOn(CartItem visitee) {
        return next.updateTaxesOn(current.updateTaxesOn(visitee));
    }
}

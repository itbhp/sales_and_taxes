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
    public CartItem visit(CartItem visitee) {
        return next.visit(current.visit(visitee));
    }
}

package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;

/**
 * @author paolo
 */
public class NothingToDoStrategy implements TaxStrategy {
    @Override
    public CartItem visit(CartItem visitee) {
        return visitee;
    }
}

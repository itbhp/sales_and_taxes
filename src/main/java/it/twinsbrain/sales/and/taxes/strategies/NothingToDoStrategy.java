package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;

public class NothingToDoStrategy implements TaxStrategy {
    @Override
    public CartItem updateTaxesOn(final CartItem visitee) {
        return visitee;
    }
}

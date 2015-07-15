package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;

public class NothingToDoStrategy implements TaxStrategy {
    @Override
    public CartItem updateTaxesOn(CartItem visitee) {
        return visitee;
    }
}

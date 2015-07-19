package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;

public interface TaxStrategy {

    CartItem updateTaxesOn(final CartItem visitee);
}

package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;

/**
 *
 * @author paolo
 */
public interface TaxStrategy {

    CartItem visit(CartItem visitee);
}
